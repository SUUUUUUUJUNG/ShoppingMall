package com.shoppingmall.domain.dto;

import com.shoppingmall.domain.dto.cart.CartListResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemsDTO {

    private Long orderItemId;
    private Long orderId;
    private String gCode;
    private String colorOption;
    private String sizeOption;
    private Integer quantity;

    public OrderItemsDTO(CartListResponseDTO responseDTO) {
        this.gCode = responseDTO.getGCode();
        this.colorOption = responseDTO.getGColor();
        this.sizeOption = responseDTO.getGSize();
        this.quantity = responseDTO.getGAmount();
    }

    public OrderItemsDTO(Long orderItemId, Long orderId, String gCode, String colorOption, String sizeOption, Integer quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.gCode = gCode;
        this.colorOption = colorOption;
        this.sizeOption = sizeOption;
        this.quantity = quantity;
    }

    public OrderItemsDTO() {
    }
}
