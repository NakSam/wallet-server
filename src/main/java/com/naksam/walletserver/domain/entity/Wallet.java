package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Money;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Wallet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Money amount;

    @Builder
    public Wallet(Long id, Money amount) {
        this.id = id;
        this.amount = amount;
    }

    public WalletInfo createInfo() {
        return new WalletInfo(this.createdTime, this.amount);
    }
}
