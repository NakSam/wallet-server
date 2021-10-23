package com.naksam.walletserver.service;

import com.google.gson.Gson;
import com.naksam.walletserver.common.HttpSupport;
import com.naksam.walletserver.domain.ClubDomain;
import com.naksam.walletserver.domain.WalletDomain;
import com.naksam.walletserver.dto.*;
import com.naksam.walletserver.feign.AccountRetryClient;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletDomain walletDomain;
    private final ClubDomain clubDomain;
    private AccountRetryClient accountRetryClient;
    private static final String COOKIE_NAME = "naksam";

    public WalletInfo findMyWalletInfo(HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        return walletDomain.findMyWalletInfo(memberPayload);
    }

    public WalletHistory findMyWalletHistory(HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        return walletDomain.findMyWalletHistory(memberPayload);
    }

    @Transactional
    public void depositToClub(DepositToClub depositToClub, HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        walletDomain.depositToClub(memberPayload, depositToClub);
    }

    @Transactional
    public void depositToMe(DepositToMe depositToMe, HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        walletDomain.depositToMe(memberPayload, depositToMe);
    }

    @KafkaListener(topics = "${bootcamp.club.topic}")
    @Transactional
    public void receiveJoinMessage(String message, Acknowledgment ack) {
        Gson gson = new Gson();
        try {
            System.out.println(message);
            JoinClubMessage joinClubMessage = gson.fromJson(message, JoinClubMessage.class);
            System.out.println("joinClubMessage obj from kafka: " + joinClubMessage);
            clubDomain.joinClub(joinClubMessage);
            ack.acknowledge();
        } catch (Exception e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public WalletHistory findClubWalletHistory(Long clubId, HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        return walletDomain.findClubWalletHistory(memberPayload, clubId);
    }

    public void distribute(Long clubId, HttpServletRequest req) {
        MemberPayload memberPayload = getMemberPayload(req);
        walletDomain.distribute(memberPayload, clubId);
    }

    private MemberPayload getMemberPayload(HttpServletRequest req) {
        String token = HttpSupport.getCookie(req, COOKIE_NAME)
                .orElseThrow(() -> new RuntimeException("쿠키가 없습니다"))
                .getValue();

        return accountRetryClient.findInfo(new JsonWebToken(token));
    }
}
