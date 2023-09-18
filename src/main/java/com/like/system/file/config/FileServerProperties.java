package com.like.system.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "file-server")
public class FileServerProperties {
	/**
	 * Web Server Static Resource Path
	 */
	String webStaticLocation;
	
	/**
	 * Dynamic Resource Path
	 */
	String location;	
	
	/**
	 * Client App에서 다운로드하기 위한 URL 경로
	 */
	String clientDownloadUrl;
}
