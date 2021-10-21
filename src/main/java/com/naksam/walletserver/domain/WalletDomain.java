package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.data.WalletRepository;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class WalletDomain {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    public WalletInfo findMyWalletInfo(HttpServletRequest req) {
        return userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
                .walletInfo();
    }
}
