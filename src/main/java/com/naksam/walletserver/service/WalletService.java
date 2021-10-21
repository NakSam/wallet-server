package com.naksam.walletserver.service;

import com.naksam.walletserver.common.HttpSupport;
import com.naksam.walletserver.domain.WalletDomain;
import com.naksam.walletserver.dto.JsonWebToken;
import com.naksam.walletserver.dto.MemberPayload;
import com.naksam.walletserver.dto.WalletInfo;
import com.naksam.walletserver.feign.AccountRetryClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletDomain walletDomain;
    private AccountRetryClient accountRetryClient;
    private static final String COOKIE_NAME = "naksam";

    public WalletInfo findMyWalletInfo(HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        return walletDomain.findMyWalletInfo(memberPayload);
    }

    private MemberPayload getMemberPayload(HttpServletRequest req) {
        String token = HttpSupport.getCookie(req, COOKIE_NAME)
                .orElseThrow(() -> new RuntimeException("쿠키가 없습니다"))
                .getValue();

        return accountRetryClient.findInfo(new JsonWebToken(token));
    }
}
