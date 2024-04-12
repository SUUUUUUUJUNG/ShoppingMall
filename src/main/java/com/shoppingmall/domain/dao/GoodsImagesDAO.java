package com.shoppingmall.domain.dao;

import com.shoppingmall.domain.dto.goodsImages.GoodsImagesDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GoodsImagesDAO {

    private final SqlSessionTemplate session;

//    public int create(GoodsImagesCreateRequestDTO requestDTO) {
//        return session.insert("GoodsImagesMapper.create", requestDTO);
//    }

    public int delete(Long id) {
        return session.delete("GoodsImagesMapper.delete", id);
    }

    public List<GoodsImagesDTO> findAllByGCode(String gCode) {
        return session.selectList("GoodsImagesMapper.findAllByGCode", gCode);
    }

    public void deleteAll(String code) {
        session.delete("GoodsImagesMapper.deleteAll", code);
    }

    public void create(String imagaPath, String code) {
        session.insert("GoodsImagesMapper.create",
                Map.of("imagePath", imagaPath, "code", code));
    }
}
