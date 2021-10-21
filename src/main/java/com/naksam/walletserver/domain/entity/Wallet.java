package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Money;
import lombok.Builder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Wallet implements Serializable {
    private Money amount;

    public Wallet() {
    }

    @Builder
    public Wallet(Money amount) {
        this.amount = amount;
    }

    public Money amount() {
        return amount;
    }
}
