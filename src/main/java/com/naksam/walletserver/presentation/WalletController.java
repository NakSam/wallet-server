package com.naksam.walletserver.presentation;

import com.naksam.walletserver.dto.DepositToClub;
import com.naksam.walletserver.dto.DepositToMe;
import com.naksam.walletserver.dto.WalletHistory;
import com.naksam.walletserver.dto.WalletInfo;
import com.naksam.walletserver.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/my")
    public ResponseEntity<?> findMyWalletInfo(HttpServletRequest req) {
        WalletInfo walletInfo = walletService.findMyWalletInfo(req);
        return ResponseEntity.ok(walletInfo);
    }

    @GetMapping("/my/history")
    public ResponseEntity<?> findMyWalletHistory(HttpServletRequest req) {
        WalletHistory myWalletHistory = walletService.findMyWalletHistory(req);
        return ResponseEntity.ok(myWalletHistory);
    }

    @GetMapping("/club/{clubId}/history")
    public ResponseEntity<?> findClubWalletHistory(@PathVariable Long clubId, HttpServletRequest req) {
        WalletHistory walletHistory = walletService.findClubWalletHistory(clubId, req);
        return ResponseEntity.ok(walletHistory);
    }

    @PostMapping("/my/deposit")
    public ResponseEntity<?> depositMyWallet(@RequestBody DepositToMe depositToMe, HttpServletRequest req) {
        walletService.depositToMe(depositToMe, req);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/club/deposit")
    public ResponseEntity<?> depositToClub(@RequestBody DepositToClub deposit, HttpServletRequest req) {
        walletService.depositToClub(deposit, req);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/club/{clubId}/distribute")
    public ResponseEntity<?> distribute(@PathVariable Long clubId, HttpServletRequest req) {
        walletService.distribute(clubId, req);
        return ResponseEntity.ok()
                .build();
    }
}
