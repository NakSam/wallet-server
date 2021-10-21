package com.naksam.walletserver;

import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.ClubUser;
import com.naksam.walletserver.domain.entity.User;
import com.naksam.walletserver.domain.entity.Wallet;
import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.Money;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class InitData {
    private final InitDataService initDataService;

    public InitData(InitDataService initDataService) {
        this.initDataService = initDataService;
    }

    @PostConstruct
    public void init() {
        initDataService.init();
    }

    @Component
    static class InitDataService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            Wallet wallet1 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet1);

            User user1 = User.builder()
                    .name("박준형")
                    .email("xcq@google.com")
                    .wallet(wallet1)
                    .build();
            em.persist(user1);

            Wallet wallet2 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet2);

            User user2 = User.builder()
                    .name("강민형")
                    .email("jhjd@google.com")
                    .wallet(wallet2)
                    .build();
            em.persist(user2);

            Wallet wallet3 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet3);

            User user3 = User.builder()
                    .name("김형준")
                    .email("zxcasd@google.com")
                    .wallet(wallet3)
                    .build();
            em.persist(user3);

            Wallet wallet4 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet4);

            User user4 = User.builder()
                    .name("박용수")
                    .email("asddqw@google.com")
                    .wallet(wallet4)
                    .build();
            em.persist(user4);

            Wallet wallet5 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet5);

            User user5 = User.builder()
                    .name("이서라")
                    .email("cxvxcv@google.com")
                    .wallet(wallet5)
                    .build();
            em.persist(user5);

            Wallet wallet6 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet6);

            User user6 = User.builder()
                    .name("정다솜")
                    .email("asdadsasdqw@google.com")
                    .wallet(wallet6)
                    .build();
            em.persist(user6);

            Wallet wallet7 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet7);

            Club club = Club.builder()
                    .name(new ClubName("KB 축구모임"))
                    .clubMaster(user1)
                    .wallet(wallet7)
                    .build();
            em.persist(club);

            ClubUser clubUser = ClubUser.builder()
                    .club(club)
                    .user(user1)
                    .build();
            em.persist(clubUser);

            Wallet wallet8 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet8);

            Club club1 = Club.builder()
                    .name(new ClubName("KB 맛집탐방"))
                    .clubMaster(user2)
                    .wallet(wallet8)
                    .build();
            em.persist(club1);

            ClubUser clubUser1 = ClubUser.builder()
                    .club(club1)
                    .user(user2)
                    .build();
            em.persist(clubUser1);

            Wallet wallet9 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet9);

            Club club2 = Club.builder()
                    .name(new ClubName("KB 코딩스터디"))
                    .clubMaster(user3)
                    .wallet(wallet9)
                    .build();
            em.persist(club2);

            ClubUser clubUser2 = ClubUser.builder()
                    .club(club2)
                    .user(user3)
                    .build();
            em.persist(clubUser2);

            Wallet wallet10 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet10);

            Club club3 = Club.builder()
                    .name(new ClubName("KB 야구단"))
                    .clubMaster(user4)
                    .wallet(wallet10)
                    .build();
            em.persist(club3);

            ClubUser clubUser3 = ClubUser.builder()
                    .club(club3)
                    .user(user4)
                    .build();
            em.persist(clubUser3);

            Wallet wallet11 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet11);

            User user = User.builder()
                    .name("tester")
                    .email("test@test.com")
                    .wallet(wallet11)
                    .build();

            em.persist(user);

            Wallet wallet12 = Wallet
                    .builder()
                    .amount(Money.wons(1_000_000))
                    .build();
            em.persist(wallet12);

            Club club4 = Club.builder()
                    .name(new ClubName("KB 맛집탐방"))
                    .clubMaster(user)
                    .wallet(wallet12)
                    .build();
            em.persist(club4);
        }
    }
}
