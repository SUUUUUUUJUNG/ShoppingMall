package com.shoppingmall.security.oauth2;

import java.util.Map;

public interface SocialOauth2Service {

    String login(Map<String, Object> attributes);
}
