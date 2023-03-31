package com.example.openfeignweb.openfeign;

import com.example.openfeignweb.pojo.dto.OpenFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "payment-provide-one")//server的名称 服务名不能用下划线
public interface OpenfeignService {

    /**
     * 不带参数的openfeign调用
     * @return String
     */
    @GetMapping("/openfeign/findById")
    String testOpenfeign(@RequestParam Integer id);

    /**
     * 带参数的openfeign调用
     */
    @GetMapping("/openfeign/findByBodyAndParam")
    List<String> findByBodyAndParam(@RequestParam Integer id,
                                    @RequestBody OpenFeignDTO openFeignDTO);

}


