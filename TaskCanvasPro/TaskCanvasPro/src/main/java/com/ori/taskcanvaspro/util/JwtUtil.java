package com.ori.taskcanvaspro.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class JwtUtil {

    private static final Set<String> tokenSet = new HashSet<>();

    public static String createToken(){
        String token = UUID.randomUUID().toString();
        tokenSet.add(token);
        return token;
    }
}
