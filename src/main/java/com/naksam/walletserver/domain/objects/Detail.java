package com.naksam.walletserver.domain.objects;

public enum Detail {
        DEPOSIT("입금"),
        WITHDRAWAL("출금");

        private final String content;

        Detail(String content) {
            this.content = content;
        }

        public String content() {
            return content;
        }
    }