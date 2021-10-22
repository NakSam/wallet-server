package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class DepositHistory {
    private final Long id;
    private final Money amount;
    private final LocalDateTime dateTime;
    private final ClubName clubName;
    private final String type;

    public DepositHistory(Long id, Money amount, LocalDateTime dateTime, ClubName clubName, String type) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.clubName = clubName;
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount.amount();
    }

    public String getClubName() {
        return clubName.content();
    }
}