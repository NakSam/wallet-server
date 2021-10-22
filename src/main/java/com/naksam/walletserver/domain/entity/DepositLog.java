package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Money;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class DepositLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_log_id")
    private Long id;

    @ManyToOne
    private Club club;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Detail detail;

    private Money amount;

    @Builder
    public DepositLog(Long id, Club club, User user, Detail detail, Money amount) {
        this.id = id;
        this.club = club;
        this.user = user;
        this.detail = detail;
        this.amount = amount;
    }

    public enum Detail {
        CLUB_TO_USER,
        USER_TO_CLUB;

//        CLUB_TO_USER(Action.DEPOSIT, Action.WITHDRAWAL),
//        USER_TO_CLUB(Action.WITHDRAWAL, Action.DEPOSIT);
//
//        private final Action fromUser;
//        private final Action fromClub;
//
//        Detail(Action fromUser, Action fromClub) {
//            this.fromUser = fromUser;
//            this.fromClub = fromClub;
//        }
//
//        public enum Action {
//            DEPOSIT("입금"),
//            WITHDRAWAL("출금");
//
//            private final String content;
//
//            Action(String content) {
//                this.content = content;
//            }
//
//            public String content() {
//                return this.content;
//            }
//        }
    }
}
