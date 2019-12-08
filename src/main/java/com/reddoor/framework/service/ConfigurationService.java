package com.reddoor.framework.service;

import java.util.List;

import com.reddoor.framework.domain.Configuration;

public interface ConfigurationService {
	List<Configuration> findAll() throws Exception;
}
