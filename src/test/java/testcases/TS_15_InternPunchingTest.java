package testcases;

import org.testng.annotations.BeforeClass;
import pageObjects.CR_Dashboard_Nikhil;
import utilities.InternDataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.InternDashboard;
import pageObjects.UserLogin;
import testbases.BaseClass;

public class TS_15_InternPunchingTest extends BaseClass {
    String validToken;
    InternDashboard db ;
    UserLogin login_page_obj;
    String invalidToken = "This is Invalid Token";
    CR_Dashboard_Nikhil crdb;
    @BeforeClass
    void getTodayValidToken(){
        login_page_obj = new UserLogin(driver);
        login_page_obj.clickUser();
        login_page_obj.sendUserName("2494444");
        login_page_obj.sendUserPassword("123456");
        login_page_obj.clickUserLoginButton();
        crdb = new CR_Dashboard_Nikhil(driver);
        validToken=crdb.getTokenGenerateIfNot();
        crdb.logout();
    }

    @BeforeMethod
    void clickUser(){
        login_page_obj = new UserLogin(driver);
        login_page_obj.clickUser();

    }

    void loginAsUser(String username, String password){
        login_page_obj.sendUserName(username);
        login_page_obj.sendUserPassword(password);
        login_page_obj.clickUserLoginButton();
        db = new InternDashboard(driver);

    }

    @Test( priority = 1, dataProvider = "firstTimePunchInInValidTokenData", dataProviderClass = InternDataProviders.class)
    void TC_INT_042_invalid_punchIn_test(String username,String password)
    {
        loginAsUser(username, password);
        db.waitTillTokenInputLoads();
        db.sendTokenInput(invalidToken);
        db.clickPunchIn();
        try{
            db.waitTillInvalidTokenMsgAppears();
            String status = db.getAttendanceStatus();
            Assert.assertEquals(status, "not_marked");
        }

        finally {
            db.logout();
        }
    }


    @Test( priority = 2, dataProvider = "firstTimePunchInValidTokenData", dataProviderClass = InternDataProviders.class)
    void TC_INT_041_valid_punchIn_test(String username,String password)
    {
        //System.out.println(username+" "+password);
        loginAsUser(username, password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //db.waitTillPunchInBtnLoads();
        String prev = db.getAttendanceStatus();
        try {


            if (prev.equalsIgnoreCase("not_marked")) {

                db.sendTokenInput(validToken);
                db.clickPunchIn();
                db.waitTillStatusTurnsPresent();

                String status = db.getAttendanceStatus();
                Assert.assertEquals(status, "present");

            }
            else{
                System.out.println("Already Marked");
            }
        }  finally {
            db.logout();
        }


    }
    @Test(priority = 3, dataProvider = "firstTimePunchInValidTokenData", dataProviderClass = InternDataProviders.class)
    void TC_INT_043_already_attendance_marked_test(String username,String password) {
        loginAsUser(username, password);

        try{
            String status=db.isAlreadyPunchedIn();
            Assert.assertEquals(status,"present");
        }

        finally {
            db.logout();
        }
    }


}
