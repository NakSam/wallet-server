package com.naksam.walletserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberPayload {
    private Long id;
    private String email;
    private String name;
}