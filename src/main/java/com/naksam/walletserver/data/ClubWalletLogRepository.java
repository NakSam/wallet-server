package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.ClubWalletLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubWalletLogRepository extends JpaRepository<ClubWalletLog, Long> {
}
