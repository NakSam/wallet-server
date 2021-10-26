package com.naksam.walletserver.dto;

import lombok.Data;

@Data
public class Payment {
    private String storeName;
    private Long money;
}
