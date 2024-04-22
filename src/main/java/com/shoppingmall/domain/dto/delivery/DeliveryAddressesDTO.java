package com.shoppingmall.domain.dto.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryAddressesDTO {

    private Long id;
    private Long memberId;
    private String address;
    private String addr_Detail;
    private String zip_Code;
    private String phoneNumber;
    private String recipient_name;
}
