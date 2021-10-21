package com.naksam.walletserver.service;

import com.naksam.walletserver.domain.WalletDomain;
import com.naksam.walletserver.dto.WalletInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletDomain walletDomain;

    public WalletInfo findMyWalletInfo(HttpServletRequest req) {
        return walletDomain.findMyWalletInfo(req);
    }
}
