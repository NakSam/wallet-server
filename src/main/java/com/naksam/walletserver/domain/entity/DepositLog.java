package com.naksam.walletserver.domain.entity;

import javax.persistence.*;

@Entity
public class DepositLog extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_log_id")
    private Long id;


}
