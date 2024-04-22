package com.shoppingmall.domain.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryAddressesCreateRequestDTO {

    private Long memberId;
    private String address;
    private String addr_Detail;
    private String zip_Code;
    private String phoneNumber;
    private String recipient_name;
}
