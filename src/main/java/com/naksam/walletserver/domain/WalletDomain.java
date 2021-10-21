package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.ClubUserRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.dto.MemberPayload;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletDomain {
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final ClubUserRepository clubUserRepository;

    public WalletInfo findMyWalletInfo(MemberPayload memberPayload) {
        return userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
                .walletInfo();
//        return userRepository.findById(memberPayload.getId())
//                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
//                .walletInfo();
    }
}
