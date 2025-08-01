package com.lbk.sharper.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**

 * @author WJX
 * @Description token
 * @Date 2023-05-23
 * @Time 23:54
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
