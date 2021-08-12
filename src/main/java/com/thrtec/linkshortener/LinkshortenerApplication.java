package com.thrtec.linkshortener;

import io.lettuce.core.ClientOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class LinkshortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkshortenerApplication.class, args);
	}

	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		System.out.println("connecting with redis");
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis", 6379);
		return redisStandaloneConfiguration;
	}

	@Bean
	public ClientOptions clientOptions() {
		return ClientOptions.builder()
				.disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
				.autoReconnect(true)
				.build();
	}

	@Bean
	public RedisConnectionFactory connectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {

		LettuceClientConfiguration configuration = LettuceClientConfiguration.builder()
				.clientOptions(clientOptions()).build();

		return new LettuceConnectionFactory(redisStandaloneConfiguration, configuration);
	}

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	@Primary
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}


}
