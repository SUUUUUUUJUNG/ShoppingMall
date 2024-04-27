package com.shoppingmall.domain.service;

import com.shoppingmall.domain.dao.EmailVerificationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationDAO emailVerificationDAO;
}
