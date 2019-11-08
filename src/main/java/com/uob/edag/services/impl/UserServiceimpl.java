package com.uob.edag.services.impl;

import javax.security.auth.login.LoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.uob.edag.services.User;
import com.uob.edag.services.UserService;
import com.uob.edag.util.ActiveDirectoryAuthentication;
import com.uob.edag.util.ErrorMessage;
import com.uob.edag.util.PropertyLoadUtil;

@Service
public class UserServiceimpl implements UserService {
	 private static final Logger logger = LogManager.getLogger(UserServiceimpl.class);
	


	@Override
	public ErrorMessage getUserById(User user) {
		ErrorMessage authResult = null;
		final String LADP_DOMAIN = PropertyLoadUtil.getProperty(user.getDomain()+"."+"LADP_DOMAIN");
		ActiveDirectoryAuthentication authentication = new ActiveDirectoryAuthentication(LADP_DOMAIN);
		try {
			logger.info(user.getIp());
			
			authResult = authentication.authenticate(user);
		//	logUserDetails(user);
		} catch (LoginException e) {
			e.printStackTrace();
		}

		return authResult;
	}



}
