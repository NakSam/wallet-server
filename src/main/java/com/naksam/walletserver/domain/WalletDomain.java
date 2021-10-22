package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.ClubUserRepository;
import com.naksam.walletserver.data.DepositLogRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.DepositLog;
import com.naksam.walletserver.domain.entity.User;
import com.naksam.walletserver.dto.Deposit;
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
    private final DepositLogRepository depositLogRepository;

    public WalletInfo findMyWalletInfo(MemberPayload memberPayload) {
        return userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
                .walletInfo();
//        return userRepository.findById(memberPayload.getId())
//                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
//                .walletInfo();
    }

    public void deposit(MemberPayload memberPayload, Deposit deposit) {
        User user = userRepository.findById(memberPayload.getId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        Club club = clubRepository.findById(deposit.getClubId())
                .orElseThrow(() -> new RuntimeException("모임이 없습니다"));

        boolean exist = clubUserRepository.findByClubAndUser(club, user)
                .isPresent();

        if (userIsNotInClub(exist)) {
            throw new RuntimeException("클럽에 가입된 회원이 아닙니다");
        }

        DepositLog depositLog = user.depositToClub(deposit.getMoney(), club);
        depositLogRepository.save(depositLog);
    }

    private boolean userIsNotInClub(boolean exist) {
        return !exist;
    }
}
