package com.minitao.order.rpc;

import com.item.common.dto.StockDto;
import com.minitao.common.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("minitao-item")
public interface StockService {

    @PostMapping("/stock/subtract")
    CommonResult subtractStock(@RequestBody StockDto stockDto);
}
