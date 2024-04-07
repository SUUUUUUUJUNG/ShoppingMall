package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.ReviewDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.ReviewService;
import com.shoppingmall.web.service.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewAPIController {

    private final MemberLoginService memberLoginService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewDTO reviewDTO, HttpSession session){
        MemberDTO login = memberLoginService.getLogin(session);
        reviewService.create(new ReviewDTO(login.getMemberId()));
        return ResponseEntity.ok(Map.of("message","리뷰가 등록되었습니다."));
    }

}
