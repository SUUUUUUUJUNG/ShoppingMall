package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.goodsImages.GoodsImagesCreateRequestDTO;
import com.shoppingmall.domain.dto.goodsImages.GoodsImagesDTO;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsImagesService;
import com.shoppingmall.domain.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods-images")
public class GoodsImagesAPIController {

    private final GoodsImagesService goodsImagesService;
    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody GoodsImagesCreateRequestDTO requestDTO, Principal principal) {
        validateRole(principal);
        goodsImagesService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "사진 첨부에 성공했습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
        validateRole(principal);
        goodsImagesService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "사진 삭제에 성공했습니다."));
    }

    @PostMapping("/{gCode}")
    public ResponseEntity<?> findAllByGCode(@PathVariable("gCode") String gCode, Principal principal) {
        validateRole(principal);
        List<GoodsImagesDTO> list = goodsImagesService.findAllByGCode(gCode);
        return ResponseEntity.ok(list);
    }

    private void validateRole(Principal principal) {
        MemberDTO memberDTO = memberLoginService.findByPrinciple(principal);
        if (!memberDTO.getRole().equals("ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }
    }
}
