package com.shoppingmall.domain.enums;

public enum MemberStatus {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    BANNED("차단됨");

    private  final String description;

    MemberStatus(String description) {
        this.description = description;
    }

    public String getDescription() {return description;}
}
