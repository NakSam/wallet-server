package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.domain.objects.Money;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private Wallet wallet;

    @Builder
    public User(Long id, String name, String email, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wallet = wallet;
    }

    public boolean memberIsNotEqual(Long ownerId) {
        return !this.id.equals(ownerId);
    }

    public WalletInfo walletInfo() {
        return new WalletInfo(createdTime, wallet.amount());
    }

    public UserWalletLog deposit(Money amount) {
        return this.deposit(amount.longValue());
    }

    public UserWalletLog deposit(Long amount) {
        wallet.deposit(amount);
        return UserWalletLog.builder()
                .user(this)
                .detail(Detail.DEPOSIT)
                .amount(Money.wons(amount))
                .targetName(this.name)
                .build();
    }

    public UserWalletLog withdrawal(Long amount, String name) {
        wallet.checkMoney(amount);
        wallet.withdrawal(amount);

        return UserWalletLog.builder()
                .user(this)
                .detail(Detail.WITHDRAWAL)
                .amount(Money.wons(amount))
                .targetName(name)
                .build();
    }

    public Long id() {
        return id;
    }

    public String name() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(wallet, user.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, wallet);
    }
}
