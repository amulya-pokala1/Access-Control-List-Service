package com.accolite.miniau.accesscontrol.utility;

import javax.servlet.http.HttpSession;

import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;

public class AuthenticationUtility {

	public static void checkAuthentication(HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
	}
}
