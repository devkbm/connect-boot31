package com.like.system.core.p6spylog;

import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.p6spy.engine.spy.P6SpyOptions;

@Configuration
public class P6spyConfig {

	@PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }   
}
