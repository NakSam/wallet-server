package com.naksam.walletserver.data;

import com.naksam.walletserver.domain.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
