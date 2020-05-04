package com.gmtech.webase.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomBCryptPasswordEncoder {
	public static void main(String[] args) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("admin"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
