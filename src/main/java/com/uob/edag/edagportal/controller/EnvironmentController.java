package com.uob.edag.edagportal.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.edag.edagportal.dataload.CacheData;
import com.uob.edag.util.Constants;

@CrossOrigin(maxAge = 3600)
@RequestMapping("/environment")
@RestController
public class EnvironmentController {

	@RequestMapping("/servers")
	public String getserverDetails() {
		Object servers = "";
		try {
			
			servers = CacheData.getCachedData(Constants.EDAG_SERVERS);
		
		} catch (Exception e) {
			System.err.println(e);
		}
		return servers.toString();
	}

	@RequestMapping("/files")
	public String getFileDetails() {
		Object files = "";
		try {
			//System.out.println("test1");
			
			files = CacheData.getCachedData(Constants.EDAG_FILE_DETAILS);
			//System.out.println("test123");
			//System.out.println(cachedData.toString());
			/*
			 * cachedData = cacheData.getCachedData(Constants.EDAG_HIVE_CHECK);
			 * System.out.println("test123");
			 * System.out.println(cachedData.toString());
			 */
		} catch (Exception e) {
			System.err.println(e);
		}
		return files.toString();
	}
}
