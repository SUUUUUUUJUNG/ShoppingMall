package com.shoppingmall.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private MemberLoginService memberLoginService;

    private ReviewCreateRequestDTO requestDTO;

    private ReviewDTO reviewDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new ReviewCreateRequestDTO();
        requestDTO.setGCode("G123");
        requestDTO.setReview_Text("Great product!");
        requestDTO.setRating(5);

        reviewDTO = new ReviewDTO();
        reviewDTO.setReview_Id(1L); // 변수명 수정: setReview_Id -> setReviewId
        reviewDTO.setReview_Text("리뷰 입니다."); // 변수명 수정: setReview_Text -> setReviewText
        reviewDTO.setRating(5);
        reviewDTO.setMemberId(1L);

    }

    @Test
    @WithMockUser(username="user1",roles = "USER")
    public void createReview_Success() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setMemberId(1L);

        when(memberLoginService.findByPrinciple(any(Principal.class))).thenReturn(member);
        when(reviewService.create(any(ReviewCreateRequestDTO.class))).thenReturn(1); // Assuming the create method returns the number of rows affected

        mockMvc.perform(post("/api/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("리뷰가 등록되었습니다."));
    }

    @Test
    public void findByReviewId_Success() throws Exception {
        // Given
        long reviewId = 1L;  // Test with a specific review ID
        when(reviewService.findByReviewId(anyLong())).thenReturn(reviewDTO);  // 올바르게 설정

        // When
        mockMvc.perform(get("/api/review")
                        .param("reviewId", Long.toString(reviewId))
                        .accept(MediaType.APPLICATION_JSON))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.review_Id").value(reviewDTO.getReview_Id()))
                .andExpect(jsonPath("$.review_Text").value(reviewDTO.getReview_Text()))
                .andExpect(jsonPath("$.rating").value(reviewDTO.getRating()))
                .andExpect(jsonPath("$.memberId").value(reviewDTO.getMemberId()));
    }

    @Test
    public void findAllByGCode_Success() throws Exception {
        // Given: 준비
        String gCode = "G123";
        List<ReviewDTO> reviews = new ArrayList<>();
        ReviewDTO review1 = new ReviewDTO();
        review1.setReview_Id(1L);
        review1.setReview_Text("첫 번째 리뷰입니다.");
        review1.setRating(5);
        review1.setMemberId(1L);

        ReviewDTO review2 = new ReviewDTO();
        review2.setReview_Id(2L);
        review2.setReview_Text("두 번째 리뷰입니다.");
        review2.setRating(4);
        review2.setMemberId(2L);

        reviews.add(review1);
        reviews.add(review2);

        when(reviewService.findAllByGCode(eq(gCode))).thenReturn(reviews);

        // When & Then: 실행 및 검증
        mockMvc.perform(get("/api/review")
                        .param("gCode", gCode)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].review_Id").value(reviews.get(0).getReview_Id()))
                .andExpect(jsonPath("$[0].review_Text").value("첫 번째 리뷰입니다."))
                .andExpect(jsonPath("$[0].rating").value(5))
                .andExpect(jsonPath("$[0].memberId").value(1L))
                .andExpect(jsonPath("$[1].review_Id").value(reviews.get(1).getReview_Id()))
                .andExpect(jsonPath("$[1].review_Text").value("두 번째 리뷰입니다."))
                .andExpect(jsonPath("$[1].rating").value(4))
                .andExpect(jsonPath("$[1].memberId").value(2L));
    }

}
