package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Club extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @Column(name = "name")
    private ClubName name;

    @ManyToOne
    @JoinColumn(name = "club_master_id")
    private User clubMaster;

    private Wallet wallet;

    @Builder
    public Club(LocalDateTime createdTime, LocalDateTime modifiedTime, Long id, ClubName name, User clubMaster, Wallet wallet) {
        super(createdTime, modifiedTime);
        this.id = id;
        this.name = name;
        this.clubMaster = clubMaster;
        this.wallet = wallet;
    }

    public ClubWalletLog deposit(Long money, String targetName) {
        wallet.deposit(money);
        return ClubWalletLog.builder()
                .club(this)
                .detail(Detail.DEPOSIT)
                .amount(Money.wons(money))
                .targetName(targetName)
                .build();
    }

    public WalletInfo walletInfo() {
        return new WalletInfo(createdTime, wallet.amount());
    }

    public String name() {
        return this.name.content();
    }
}
