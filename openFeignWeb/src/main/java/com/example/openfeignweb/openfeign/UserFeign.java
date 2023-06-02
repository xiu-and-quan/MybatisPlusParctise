package com.example.openfeignweb.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "USER_APPLICATION")
public interface UserFeign {

    String QUERY_BY_OPENFEIGN = "/openfeign/findById";

    @GetMapping(QUERY_BY_OPENFEIGN)
    String testOpenfeign(@RequestParam Integer id);
}
