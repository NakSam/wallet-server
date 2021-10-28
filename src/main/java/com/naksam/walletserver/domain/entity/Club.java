package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import com.naksam.walletserver.dto.DistributeLog;
import com.naksam.walletserver.dto.Payment;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Club extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    private ClubName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_master_id")
    private User clubMaster;

    private Wallet wallet;

    private Long dues;

    @Builder
    public Club(LocalDateTime createdTime, LocalDateTime modifiedTime, Long id, ClubName name, User clubMaster, Wallet wallet, Long dues) {
        super(createdTime, modifiedTime);
        this.id = id;
        this.name = name;
        this.clubMaster = clubMaster;
        this.wallet = wallet;
        this.dues = dues;
    }

    public ClubWalletLog deposit(Long money, User target) {
        wallet.deposit(money);
        return ClubWalletLog.builder()
                .club(this)
                .detail(Detail.DEPOSIT)
                .amount(Money.wons(money))
                .targetName(target.name())
                .targetId(target.id())
                .build();
    }

    public ClubWalletLog withdrawal(Long amount, User target) {
        wallet.withdrawal(amount);

        return ClubWalletLog.builder()
                .club(this)
                .detail(Detail.WITHDRAWAL)
                .amount(Money.wons(amount))
                .targetName(target.name())
                .targetId(target.id())
                .build();
    }

    public DistributeLog distributeToMembers(List<User> users, User master, List<ClubWalletLog> undistributedLogOfClub) {
        checkMaster(master);

        List<ClubWalletLog> clubWalletLogs = new ArrayList<>();
        List<UserWalletLog> userWalletLogs = new ArrayList<>();

        users.forEach(user -> {
            Money divided = calculateDivided(user.id(), undistributedLogOfClub);
            Money amountWithPayback = moneyWithPayback(divided);
            System.out.println(user.id() + " : " + amountWithPayback.longValue());
            userWalletLogs.add(user.deposit(amountWithPayback));
            clubWalletLogs.add(withdrawal(divided.longValue(), user));
        });

        undistributedLogOfClub.forEach(ClubWalletLog::distribute);

        return new DistributeLog(userWalletLogs, clubWalletLogs);
    }

    private void checkMaster(User master) {
        System.out.println("master.id() = " + master.id());
        System.out.println("clubMaster.id() = " + clubMaster.id());
        if (!clubMaster.equals(master)) {
            throw new RuntimeException("권한이 없습니다");
        }
    }

    private Money calculateDivided(Long id, List<ClubWalletLog> undistributedLogOfClub) {
        Double rate = calculateRate(undistributedLogOfClub, id);

        if (rate == 0) {
            return Money.wons(0);
        }

        return wallet.amount()
                .divide(rate);
    }

    private Double calculateRate(List<ClubWalletLog> undistributedLogOfClub, Long userId) {
        long total = undistributedLogOfClub.stream()
                .mapToLong(clubWalletLog -> clubWalletLog.amount()
                        .longValue())
                .sum();

        long userTotal = undistributedLogOfClub.stream()
                .filter(clubWalletLog -> clubWalletLog.targetId()
                        .equals(userId))
                .mapToLong(clubWalletLog -> clubWalletLog.amount()
                        .longValue())
                .sum();

        if (userTotal == 0) {
            return 0D;
        }

        return Money.wons(total).ratio(userTotal);
    }

    private Money moneyWithPayback(Money divided) {
        if (divided.longValue()
                .equals(0L)) {
            return divided;
        }

        return divided.ceiling()
                .plus(Money.wons(100));
    }

    public WalletInfo walletInfo() {
        return new WalletInfo(createdTime, wallet.amount());
    }

    public String name() {
        return this.name.content();
    }

    public Long dues() {
        return dues;
    }

    public ClubWalletLog payment(Payment payment) {
        wallet.withdrawal(payment.getMoney());
        return ClubWalletLog.builder()
                .club(this)
                .detail(Detail.WITHDRAWAL)
                .amount(Money.wons(payment.getMoney()))
                .targetName(payment.getStoreName())
                .targetId(null)
                .build();
    }
}
