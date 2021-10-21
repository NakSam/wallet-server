package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.ClubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {
}
