package com.samsungsds.eshop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="AD")
public interface AdServiceClient {
    @GetMapping("/ads")
    List<Object> getAds();

}
