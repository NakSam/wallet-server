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


    public void checkMoney(Long amount) {
        if (amount().isLessThan(Money.wons(amount))) {
            throw new RuntimeException("잔액이 부족합니다");
        }
    }

    public void deposit(Long amount) {
        this.amount = this.amount.plus(Money.wons(amount));
    }

    public void withdrawal(Long amount) {
        this.amount = this.amount.minus(Money.wons(amount));
    }
}
