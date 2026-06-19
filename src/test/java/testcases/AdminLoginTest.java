package testcases;

import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import testbases.BaseClass;

public class AdminLoginTest extends BaseClass {
    @Test
    public void testLogin(){

        logger.info(" Admin Login test Start");
        AdminLogin lp = new AdminLogin(driver);
        lp.clickAdmin();
        lp.sendAdminName("sudo");
        lp.sendAdminPassword("racingcar123");
        lp.clickAdminLoginButton();
    }
}
