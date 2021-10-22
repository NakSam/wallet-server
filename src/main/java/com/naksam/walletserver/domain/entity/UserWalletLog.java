package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class UserWalletLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private Detail detail;

    private Money amount;

    private String targetName;

    @Builder
    public UserWalletLog(Long id, User user, Detail detail, Money amount, String targetName) {
        this.id = id;
        this.user = user;
        this.detail = detail;
        this.amount = amount;
        this.targetName = targetName;
    }
}
