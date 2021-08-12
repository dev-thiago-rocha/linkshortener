package com.thrtec.linkshortener.service;

import com.thrtec.linkshortener.util.EncodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedirectView redirectByKey(String urlEncoded) {
        final var redirectView = new RedirectView();
        redirectView.setUrl(redisTemplate.opsForValue().get(urlEncoded));
        return redirectView;
    }

    public String addUrl(String urlEncoded) {
        redisTemplate.opsForValue().set(urlEncoded, EncodeUtil.base64UrlDecode(urlEncoded));
        return "https://link.thrtec.com/" + urlEncoded;
    }

}
