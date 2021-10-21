package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.MemberNumber;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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
}
