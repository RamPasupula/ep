package com.uob.edag.edagportal.dataload;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoadDataInMemory {
	private static final Logger log = LoggerFactory.getLogger(LoadDataInMemory.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@PostConstruct
	public void init() {
		log.info("init() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance();
			cacheData.loadJsonMap();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("init() call completed: The time is now {}", dateFormat.format(new Date()));
	}
}
