package com.tesobe.obp;

import com.tesobe.obp.auth.DirectAuthenticationService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTestSupport {
    protected String authToken;

    @Value("${obp.username}")
    private String username;

    @Value("${obp.password}")
    private String password;

    @Autowired
    private DirectAuthenticationService directAuthenticationService;

    @Before
    public void init() {
    	System.out.println("inside intialisation *******************************************************");
    	System.out.println("username ********************* "+username);
    	System.out.println("password ********************* "+password);
    	authToken = directAuthenticationService.login(username, password);
    	
    	System.out.println("authToken ********************* "+authToken);
    }
}
