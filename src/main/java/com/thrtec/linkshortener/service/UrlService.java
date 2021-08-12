package com.thrtec.linkshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedirectView redirectByKey(String key) {
        final var redirectView = new RedirectView();
        redirectView.setUrl("https://" + redisTemplate.opsForValue().get(key));
        return redirectView;
    }

    public String addUrl(String url) {
        UUID uuid = UUID.randomUUID();
        String randomKey = uuid.toString();
        redisTemplate.opsForValue().set(randomKey, url);
        return "https://link.thrtec.com/" + randomKey;
    }

}
