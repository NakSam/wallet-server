package com.naksam.walletserver.presentation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WalletController {

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().build();
    }

}
