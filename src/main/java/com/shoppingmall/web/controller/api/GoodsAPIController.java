package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.dto.goods.*;
import com.shoppingmall.domain.dto.member.MemberDTO;
import com.shoppingmall.domain.service.GoodsService;
import com.shoppingmall.domain.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
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

    @GetMapping
    public ResponseEntity<?> findByCode(@RequestParam("gCode") String gCode) {
        GoodsDTO goodsDTO = goodsService.findByCode(gCode);
        GoodsResponseDTO responseDTO = new GoodsResponseDTO(goodsDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        List<GoodsResponseNewDTO> list = goodsService.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{gCode}")
    public ResponseEntity<?> delete(@PathVariable("gCode") String gCode, Principal principal){
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        if (!Objects.equals(member.getRole(), "ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
        goodsService.delete(gCode);
        return ResponseEntity.ok(Map.of("message", "상품이 삭제되었습니다."));
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody GoodsUpdateRequestDTO requestDTO, Principal principal){
        MemberDTO member = memberLoginService.findByPrinciple(principal);
        if (!Objects.equals(member.getRole(), "ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
        goodsService.update(requestDTO);
        return ResponseEntity.ok(Map.of("message","상품이 수정되었습니다."));
    }
}
