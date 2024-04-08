package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.MemberLoginService;
import com.shoppingmall.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewAPIController {

    private final MemberLoginService memberLoginService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewCreateRequestDTO requestDTO, Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        requestDTO.setMemberId(memberId);
        reviewService.create(requestDTO);
        return ResponseEntity.ok(Map.of("message","리뷰가 등록되었습니다."));
    }

    @GetMapping("/one")
    public ResponseEntity<?> findByReviewId(@RequestParam("reviewId") Long reviewId){
        return ResponseEntity.ok(reviewService.findByReviewId(reviewId));
    }

    @GetMapping
    public ResponseEntity<?> findAllByGCode(@RequestParam("gCode") String gCode) {
        return ResponseEntity.ok(reviewService.findAllByGCode(gCode));
    }


}
