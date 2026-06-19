package testcases;

import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.UserLogin;
import testbases.BaseClass;

import java.util.Base64;

public class UserLoginTest extends BaseClass {
    @Test
    public void loginTest(){
        logger.info(" Admin Login test Start");
        UserLogin ul = new UserLogin(driver);
        ul.clickUser();
        ul.sendUserName("");
        ul.sendUserPassword("");
        ul.clickUserLoginButton();
    }
}
