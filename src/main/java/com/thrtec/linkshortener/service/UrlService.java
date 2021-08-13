package com.thrtec.linkshortener.service;

import com.thrtec.linkshortener.util.EncodeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedirectView redirectByKey(String key) {
        final var redirectView = new RedirectView();
        redirectView.setUrl(redisTemplate.opsForValue().get(key));
        return redirectView;
    }

    public String addUrl(String urlEncoded) {
        final var key = RandomStringUtils.randomAlphabetic(5);
        redisTemplate.opsForValue().set(key, EncodeUtil.base64UrlDecode(urlEncoded));
        return "https://link.thrtec.com/" + key;
    }

}
