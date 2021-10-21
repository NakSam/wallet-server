package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.objects.Money;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletInfo {
    private final LocalDateTime createdAt;
    private final Money amount;

    public Long getAmount() {
        return amount.amount()
                .longValue();
    }
}
