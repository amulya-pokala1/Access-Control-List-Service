/*
 * 
 */
package com.accolite.miniau.accesscontrol.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringConfig.
 */
@Configuration
@EnableAsync
public class SpringConfig {

	/**
	 * Thread pool task executor.
	 *
	 * @return the executor
	 */
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

}
