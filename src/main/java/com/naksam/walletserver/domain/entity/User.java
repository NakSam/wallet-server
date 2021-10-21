package com.naksam.walletserver.domain.entity;

import com.naksam.walletserver.dto.WalletInfo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
