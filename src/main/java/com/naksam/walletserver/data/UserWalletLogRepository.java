package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.UserWalletLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletLogRepository extends JpaRepository<UserWalletLog, Long> {
}
