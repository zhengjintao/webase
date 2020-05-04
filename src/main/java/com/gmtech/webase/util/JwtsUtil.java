package com.gmtech.webase.util;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtsUtil {
	private static final String secretkey = "MyJwtSecret";
	
	public static String encode(String subject){
		return Jwts.builder()
	    .setSubject(subject)
	    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
	    .signWith(SignatureAlgorithm.HS512, secretkey)
	    .compact();
	}
	
	public static String decode(String token){
		return Jwts.parser()
	     .setSigningKey(secretkey)
	     .parseClaimsJws(token)
	     .getBody()
	     .getSubject();
	}
}
