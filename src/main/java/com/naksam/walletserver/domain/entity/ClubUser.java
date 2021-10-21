package com.naksam.walletserver.domain.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClubUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public ClubUser(Long id, Club club, User user) {
        this.id = id;
        this.club = club;
        this.user = user;
    }
}
