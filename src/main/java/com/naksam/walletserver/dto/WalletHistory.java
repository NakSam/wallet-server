package com.naksam.walletserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WalletHistory {
    private final Long amount;
    @Setter
    private List<DepositHistory> depositHistories = new ArrayList<>();

    public WalletHistory(Long amount) {
        this.amount = amount;
    }
}
