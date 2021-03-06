package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.ClubUserRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.ClubUser;
import com.naksam.walletserver.domain.entity.User;
import com.naksam.walletserver.domain.entity.Wallet;
import com.naksam.walletserver.domain.objects.ClubName;
import com.naksam.walletserver.domain.objects.Money;
import com.naksam.walletserver.dto.CreateClubMessage;
import com.naksam.walletserver.dto.JoinClubMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClubDomain {
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final ClubUserRepository clubUserRepository;

    public void joinClub(JoinClubMessage joinClubMessage) {
        User user = userRepository.findById(joinClubMessage.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        Club club = clubRepository.findById(joinClubMessage.getClubId())
                .orElseThrow(() -> new RuntimeException("모임이 없습니다"));

        boolean isPresent = clubUserRepository.findByClubAndUser(club, user).isPresent();
        if (isPresent) {
            return;
        }

        ClubUser clubUser = ClubUser.builder()
                .club(club)
                .user(user)
                .build();

        clubUserRepository.save(clubUser);
    }

    public void createClub(CreateClubMessage createClubMessage) {
        User user = userRepository.findById(createClubMessage.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

        Club club = Club.builder()
                .clubMaster(user)
                .name(new ClubName(createClubMessage.getClubName()))
                .wallet(new Wallet(Money.ZERO))
                .dues(createClubMessage.getAmount())
                .build();

        clubRepository.save(club);

        ClubUser clubUser = ClubUser.builder()
                .club(club)
                .user(user)
                .build();

        clubUserRepository.save(clubUser);
    }
}
