package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ChangePassword;
import pageObjects.InternDashboard;
import pageObjects.POC_Dashboard_Nikhil;
import pageObjects.UserLogin;
import testbases.BaseClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TS_14_01_FirstTimeLoginTest extends BaseClass {
    InternDashboard db ;
    UserLogin login_page_obj;
    POC_Dashboard_Nikhil poc_db;
    void clickUser(){
        login_page_obj = new UserLogin(driver);
        login_page_obj.clickUser();
    }
    void loginAsUser(String username, String password){
        login_page_obj.sendUserName(username);
        login_page_obj.sendUserPassword(password);
        login_page_obj.clickUserLoginButton();

    }
//    Test79981 YuBFRNm5
    @Test()
    void TC_INT_038_first_time_login_test() throws InterruptedException {
        clickUser();
        loginAsUser("123","12345678");
        poc_db = new POC_Dashboard_Nikhil(driver);
        // 2. Wait 2 seconds for the enterprise popup to completely render
        Thread.sleep(2000);

        try {
            Robot robot = new Robot();
            // Press 'Enter' or 'Escape' to dismiss the native window
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("Hardware Enter key sent to bypass enterprise popup.");
        } catch (Exception e) {
            System.out.println("Robot class blocked by corporate security policy.");
        }
        int uniqueId = new Random().nextInt(99999);
        String id = "Test"+uniqueId;
        String fullname = "Emp-"+uniqueId;
        String email = uniqueId+"@example.com";
        String mobile = "9087654321";
        String tempPassword = poc_db.OnboardNewInternAndReturnTempPassword(id,fullname,email,mobile);
        poc_db.logout();
        clickUser();
        loginAsUser(id,tempPassword);
        ChangePassword cp = new ChangePassword(driver);
        cp.setNewPassword();
        db = new InternDashboard(driver);
        Assert.assertEquals(db.getInternName(),fullname);
    }
}
