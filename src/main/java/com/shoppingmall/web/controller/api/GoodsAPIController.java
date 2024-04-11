package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.goods.GoodsCreateRequestDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsService;
import com.shoppingmall.domain.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsAPIController {

    private final GoodsService goodsService;
    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody GoodsCreateRequestDTO requestDTO, Principal principal) {
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        if (!Objects.equals(member.getRole(), "ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
        goodsService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","상품이 정상적으로 등록되었습니다."));
    }
}
