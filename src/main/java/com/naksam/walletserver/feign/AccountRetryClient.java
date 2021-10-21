package com.naksam.walletserver.feign;

import com.naksam.walletserver.config.FeignConfiguration;
import com.naksam.walletserver.config.FeignRetryConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "retry", url="${ACCOUNT_HOST:http://naksam.169.56.174.130.nip.io/account}", configuration = {FeignConfiguration.class, FeignRetryConfiguration.class})
public interface AccountRetryClient extends AccountClient {

}
