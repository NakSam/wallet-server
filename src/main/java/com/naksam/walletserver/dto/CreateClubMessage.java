package com.naksam.walletserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateClubMessage {
    private Long userId;
    private Long clubId;
    private String clubName;
    private Long amount;
}
