package com.allemon.notesweb.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTTokenUtilTest {
    @Test
    void testToken() {
        String xd = "Gowno";
        JWTTokenUtil jwtTokenUtil = new JWTTokenUtil();
        String xd2 = jwtTokenUtil.createToken(xd);
        System.out.println(xd2);
    }
}