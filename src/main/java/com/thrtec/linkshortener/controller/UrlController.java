package com.thrtec.linkshortener.controller;

import com.thrtec.linkshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/short/{url}")
    public String shortUrl(@PathVariable("url") String url){
        return urlService.addUrl(url);
    }

    @GetMapping("/{key}")
    public RedirectView redirect(@PathVariable("key") String key){
        return urlService.redirectByKey(key);
    }
}
