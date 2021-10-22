package com.naksam.walletserver.presentation;

import com.naksam.walletserver.dto.Deposit;
import com.naksam.walletserver.dto.WalletHistory;
import com.naksam.walletserver.dto.WalletInfo;
import com.naksam.walletserver.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @PostMapping("/deposit")
    public ResponseEntity<?> depositPoint(@RequestBody Deposit deposit, HttpServletRequest req) {
        walletService.deposit(deposit, req);
        return ResponseEntity.ok()
                .build();
    }
}
