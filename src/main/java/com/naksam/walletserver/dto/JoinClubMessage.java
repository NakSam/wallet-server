package com.naksam.walletserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinClubMessage {
    private Long userId;
    private Long clubId;
}
