package com.rasysbox.ws.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "springdoc.properties")
public class OpenApiProperties {
	private String projectName;
	private String projectShortDescription;
	private String developerName;
	private String developerMail;
	private String projectTosMsg;
	private String projectTosLink;
	private String projectLicenceMsg;
	private String projectLicenceLink;
	private String organizationUrl;
}
