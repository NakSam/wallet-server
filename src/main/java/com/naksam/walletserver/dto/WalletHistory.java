package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.objects.Money;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WalletHistory {
    private final Long userId;
    private final Money amount;
    @Setter
    private List<DepositHistory> depositHistories = new ArrayList<>();

    public WalletHistory(Long userId, Money amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount.amount();
    }
}
