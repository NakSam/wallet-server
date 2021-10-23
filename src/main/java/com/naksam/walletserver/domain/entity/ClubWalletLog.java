package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClubWalletLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @Enumerated(EnumType.STRING)
    private Detail detail;

    private Money amount;

    private Long targetId;

    private String targetName;

    private Boolean distributed;

    @Builder
    public ClubWalletLog(Long id, Club club, Detail detail, Money amount, Long targetId, String targetName) {
        this.id = id;
        this.club = club;
        this.detail = detail;
        this.amount = amount;
        this.targetId = targetId;
        this.targetName = targetName;
        this.distributed = false;
    }

    public void distribute() {
        this.distributed = true;
    }

    public Money amount() {
        return this.amount;
    }

    public Long targetId() {
        return this.targetId;
    }
}
