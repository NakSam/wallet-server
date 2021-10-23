package com.naksam.walletserver.dto;

import com.naksam.walletserver.domain.entity.ClubWalletLog;
import com.naksam.walletserver.domain.entity.UserWalletLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DistributeLog {
    private final List<UserWalletLog> userWalletLogs;
    private final List<ClubWalletLog> clubWalletLogs;
}
