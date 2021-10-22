package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.Club;
import com.naksam.walletserver.domain.entity.ClubUser;
import com.naksam.walletserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {
    Optional<ClubUser> findByClubAndUser(Club club, User user);
}
