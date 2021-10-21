package com.naksam.walletserver.domain;

import com.naksam.walletserver.data.ClubRepository;
import com.naksam.walletserver.data.UserRepository;
import com.naksam.walletserver.data.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletDomain {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
}
