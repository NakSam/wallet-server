package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
