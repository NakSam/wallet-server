package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.ClubUserRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.ClubUser;
import com.naksam.walletserver.domain.entity.User;
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

        ClubUser clubUser = ClubUser.builder()
                .club(club)
                .user(user)
                .build();

        clubUserRepository.save(clubUser);
    }
}
