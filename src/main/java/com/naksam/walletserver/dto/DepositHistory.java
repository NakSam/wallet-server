package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DepositHistory {
    private final Long id;
    private final Money amount;
    private final LocalDateTime dateTime;
    private final String targetName;
    private final Detail type;

    public BigDecimal getAmount() {
        return amount.amount();
    }

    public String getType() {
        return type.content();
    }
}