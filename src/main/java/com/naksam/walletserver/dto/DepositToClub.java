package com.naksam.walletserver.dto;

import lombok.Data;

@Data
public class DepositToClub {
    private Long clubId;
    private Long money;
}
