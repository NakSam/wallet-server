package com.naksam.walletserver.service;

import com.naksam.walletserver.domain.WalletDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletDomain walletDomain;
}
