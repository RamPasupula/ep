/**
 * 
 */
package com.uob.edag.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uob.edag.services.User;


/**
 * @author vencp9
 *
 */
public class ActiveDirectoryAuthentication {

	 private static final Logger logger = LogManager.getLogger(ActiveDirectoryAuthentication.class);

	//private String[] powerBIUserGroups = {"uCTX_EDA_PBI_UAT"};

	private static final String CONTEXT_FACTORY_CLASS = PropertyLoadUtil.getProperty("CONTEXT_FACTORY_CLASS");
	
	private  String ldapServerUrls[];

	private int lastLdapUrlIndex;

	private final String domainName;

	public ActiveDirectoryAuthentication(String domainName) {
		this.domainName = domainName.toUpperCase();

		try {
			ldapServerUrls = nsLookup(domainName);
		} catch (Exception e) {logger.error(e.getMessage());
		}
		lastLdapUrlIndex = 0;
	}

	public ErrorMessage authenticate(User user) throws LoginException {
		
		final String usersContainer = PropertyLoadUtil.getProperty(user.getDomain()+"."+"usersContainer");
	   final String powerBIUserGroups = PropertyLoadUtil.getProperty(user.getDomain()+"."+"powerBIUserGroups");
		ErrorMessage errorMessage = null;
		if (ldapServerUrls == null || ldapServerUrls.length == 0) {
			throw new AccountException("Unable to find ldap servers");
		}
		
		String username = user.getUserid();
		String password = user.getPasswd();
		if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
			throw new FailedLoginException("Username or password is empty");
		}
		int currentLdapUrlIndex = lastLdapUrlIndex;
		int retryCount = 0;
		   String[] attrIDs = {"cn"};
		  String[] attributes = {"memberOf"};
		  SearchControls ctls = new SearchControls();
		  Hashtable<Object, Object> env = null;
		  DirContext ctx = null;
		  String container = usersContainer.replaceAll("\"", "");
		  
		do {
			
			retryCount++;
			try {
				env = new Hashtable<Object, Object>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY_CLASS);
				env.put(Context.PROVIDER_URL, ldapServerUrls[currentLdapUrlIndex]);
				env.put(Context.SECURITY_PRINCIPAL, username + "@" + domainName);
				env.put(Context.SECURITY_CREDENTIALS, password);
				ctx = new InitialDirContext(env);

				ctls.setReturningAttributes(attrIDs);
				ctls.setReturningAttributes(attributes);
				ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

				NamingEnumeration<?> answer = ctx.search(container,
						"(&(objectclass=user)(sAMAccountName=" + username + "))", ctls);
				String[] groupname = null;
				while (answer.hasMore()) {
					SearchResult rslt = (SearchResult) answer.next();
					Attributes attrs = rslt.getAttributes();

					String groups = attrs.get("memberOf").toString();
					groupname = groups.split(":");
					String[] users= powerBIUserGroups.split(",");
					for(String userGroup : users) {
					if (null != groupname[1] && groupname[1].contains(userGroup)) {
						errorMessage = new ErrorMessage();
						errorMessage.setError(false);
						errorMessage.setValid(true);
						errorMessage.setErrorReason("SUCCESS");
						errorMessage.setErrorCode("201");
						return errorMessage;
					}
					}
				}
				ctx.close();
				lastLdapUrlIndex = currentLdapUrlIndex;
				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setValid(true);
				errorMessage.setErrorReason("Authorization Failed");
				errorMessage.setErrorCode("401");
				return errorMessage;
			} catch (CommunicationException exp) {
				logger.error(exp.getExplanation());
				logger.error(":: Error cause :: " + exp.getCause() + "  ::error Message:: " + exp.getMessage());
				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setValid(false);
				errorMessage.setErrorReason("CommunicationException");
				errorMessage.setErrorCode("500");
				return errorMessage;
			} catch (javax.naming.AuthenticationException authExp) {
				logger.error(authExp.getExplanation());
				logger.error(":: Error cause :: " + authExp.getCause() + "  ::error Message:: " + authExp.getMessage());
				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setValid(false);
				errorMessage.setErrorReason("Invalid Username Or Password");
				errorMessage.setErrorCode("401");
				return errorMessage;
			} catch (Throwable throwable) {
				logger.error(
						":: Error cause :: " + throwable.getCause() + "  ::error Message:: " + throwable.getMessage());
				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setValid(false);
				errorMessage.setErrorReason("Unknown Error");
				errorMessage.setErrorCode("500");
				return errorMessage;
			}
		} while (true);
	}

	private static String[] nsLookup(String argDomain) throws Exception {
		try {
			Hashtable<Object, Object> env = new Hashtable<Object, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
			env.put("java.naming.provider.url", "dns:");
			DirContext ctx = new InitialDirContext(env);
			Attributes attributes = ctx
					.getAttributes(String.format("_ldap._tcp.%s", argDomain), new String[] { "srv" });
			
			for (int i = 0; i < 3; i++) {
				Attribute a = attributes.get("srv");
				if (a != null) {
					List<String> domainServers = new ArrayList<String>();
					NamingEnumeration<?> enumeration = a.getAll();
					while (enumeration.hasMoreElements()) {
						String srvAttr = (String) enumeration.next();
						String values[] = srvAttr.toString().split(" ");
						domainServers.add(String.format("ldap://%s:%s", values[3], values[2]));
					}
					String domainServersArray[] = new String[domainServers.size()];
					domainServers.toArray(domainServersArray);
					logger.info(domainServersArray.toString());
					return domainServersArray;
				}
			}
			throw new Exception("Unable to find srv attribute for the domain " + argDomain);
		} catch (NamingException exp) {
			throw new Exception("Error while performing nslookup. Root Cause: " + exp.getMessage(), exp);
		}
	}

	

}
