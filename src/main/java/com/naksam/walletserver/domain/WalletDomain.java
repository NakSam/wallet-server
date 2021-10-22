package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.*;
import com.naksam.walletserver.data.query.WalletQueryRepository;
import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.ClubWalletLog;
import com.naksam.walletserver.domain.entity.User;
import com.naksam.walletserver.domain.entity.UserWalletLog;
import com.naksam.walletserver.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class WalletDomain {
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final ClubUserRepository clubUserRepository;
    private final UserWalletLogRepository userWalletLogRepository;
    private final ClubWalletLogRepository clubWalletLogRepository;
    private final WalletQueryRepository walletQueryRepository;

    public WalletInfo findMyWalletInfo(MemberPayload memberPayload) {
        return userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
                .walletInfo();
//        return userRepository.findById(memberPayload.getId())
//                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."))
//                .walletInfo();
    }

    public void depositToClub(MemberPayload memberPayload, DepositToClub deposit) {
        User user = userRepository.findById(memberPayload.getId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        Club club = clubRepository.findById(deposit.getClubId())
                .orElseThrow(() -> new RuntimeException("모임이 없습니다"));

        clubUserRepository.findByClubAndUser(club, user)
                .orElseThrow(() -> new RuntimeException("클럽에 가입된 회원이 아닙니다"));

        depositToClub(deposit, user, club);
    }

    private void depositToClub(DepositToClub deposit, User user, Club club) {
        UserWalletLog userWalletLog = user.withdrawal(deposit.getMoney(), club.name());
        ClubWalletLog clubWalletLog = club.deposit(deposit.getMoney(), user.name());
        userWalletLogRepository.save(userWalletLog);
        clubWalletLogRepository.save(clubWalletLog);
    }

    public void depositToMe(MemberPayload memberPayload, DepositToMe depositToMe) {
        User user = userRepository.findById(memberPayload.getId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        UserWalletLog userWalletLog = user.deposit(depositToMe.getMoney());
        userWalletLogRepository.save(userWalletLog);
    }

    public WalletHistory findMyWalletHistory(MemberPayload memberPayload) {
        WalletHistory myWalletHistory = createUserWalletHistory(memberPayload);
        List<DepositHistory> depositHistories = walletQueryRepository.findWalletHistoryOfUser(memberPayload.getId());
        myWalletHistory.setDepositHistories(depositHistories);
        return myWalletHistory;
    }

    private WalletHistory createUserWalletHistory(MemberPayload memberPayload) {
        Long amount = userRepository.findById(memberPayload.getId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"))
                .walletInfo()
                .getAmount();

        return new WalletHistory(amount);
    }

    public WalletHistory findClubWalletHistory(MemberPayload memberPayload, Long clubId) {
        User user = userRepository.findById(memberPayload.getId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("모임이 없습니다"));

        clubUserRepository.findByClubAndUser(club, user)
                .orElseThrow(() -> new RuntimeException("클럽에 가입된 회원이 아닙니다"));

        WalletHistory walletHistory = new WalletHistory(club.walletInfo()
                .getAmount());

        walletHistory.setDepositHistories(walletQueryRepository.findWalletHistoryOfClub(clubId));

        return walletHistory;
    }
}
