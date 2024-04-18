package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.review.ReviewCreateRequestDTO;
import com.shoppingmall.domain.dto.review.ReviewDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.dto.review.ReviewUpdateRequestDTO;
import com.shoppingmall.domain.service.member.MemberLoginService;
import com.shoppingmall.domain.service.goods.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/member")
    public ResponseEntity<?> findAllByMemberId(Principal principal){
        MemberDTO login = memberLoginService.findByPrinciple(principal);
        return ResponseEntity.ok(reviewService.findAllByMemberId(login.getMemberId()));
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody ReviewUpdateRequestDTO requestDTO, Principal principal){
        validateIsReviewOwner(requestDTO.getReview_Id(), principal);
        reviewService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message","리뷰가 수정되었습니다.","review_Id",requestDTO.getReview_Id()));
    }

    @DeleteMapping("/{review_Id}")
    public ResponseEntity<?> delete(@PathVariable("review_Id") Long review_Id, Principal principal){
        validateIsReviewOwner(review_Id,principal);
        reviewService.delete(review_Id);
        return ResponseEntity.ok(Map.of("message", "리뷰가 삭제되었습니다."));
    }

    private void validateIsReviewOwner(Long review_Id, Principal principal){
        Long memberId = memberLoginService.findByPrinciple(principal).getMemberId();
        ReviewDTO reviewDTO = reviewService.findByReviewId(review_Id);

        if(reviewDTO == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"리뷰를 찾을 수 없습니다.");
        }
        if(!reviewDTO.getMemberId().equals(memberId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
    }
}
