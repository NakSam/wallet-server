package com.naksam.walletserver.feign;

import com.naksam.walletserver.dto.JsonWebToken;
import com.naksam.walletserver.dto.MemberPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "example", url = "${ACCOUNT_HOST:http://naksam.169.56.174.130.nip.io/account}")
public interface AccountClient {
    @PostMapping(value = "/info")
    MemberPayload findInfo(@RequestBody JsonWebToken jsonWebToken);
}
