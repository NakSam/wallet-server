package com.naksam.walletserver.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

public class HttpSupport {
    public static Optional<Cookie> getCookie(HttpServletRequest req, String name) {
        return Stream.of(req.getCookies())
                .filter(cookie -> name.equals(cookie.getName()) && !cookie.getValue()
                        .isEmpty())
                .findFirst();
    }
}
