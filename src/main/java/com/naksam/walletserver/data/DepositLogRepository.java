package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.DepositLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositLogRepository extends JpaRepository<DepositLog, Long> {
}
