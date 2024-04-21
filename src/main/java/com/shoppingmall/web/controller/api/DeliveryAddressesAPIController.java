package com.shoppingmall.web.controller.api;

import com.shoppingmall.domain.service.member.DeliveryAddressesService;
import com.shoppingmall.domain.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryAddressesAPIController {

    private final MemberLoginService memberLoginService;
    private DeliveryAddressesService deliveryAddressesService;
}
