package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.objects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class WalletHistory {
    private final Long userId;
    private final Money amount;
    private final List<DepositHistory> depositHistories;

    public WalletHistory(Long userId, Money amount, List<DepositHistory> depositHistories) {
        this.userId = userId;
        this.amount = amount;
        this.depositHistories = depositHistories;
    }

    public BigDecimal getAmount() {
        return amount.amount();
    }
}
