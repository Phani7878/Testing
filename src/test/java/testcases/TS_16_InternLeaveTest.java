package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.InternDashboard;
import pageObjects.UserLogin;
import testbases.BaseClass;
import utilities.InternDataProviders;

public class TS_16_InternLeaveTest extends BaseClass {
    InternDashboard db ;
    UserLogin login_page_obj;
    @BeforeClass
    void clickUser(){
        login_page_obj = new UserLogin(driver);
        login_page_obj.clickUser();
        loginAsUser("2494455","123456");

    }

    void loginAsUser(String username, String password){
        login_page_obj.sendUserName(username);
        login_page_obj.sendUserPassword(password);
        login_page_obj.clickUserLoginButton();
        db = new InternDashboard(driver);

    }

    @Test(dataProvider = "validLeaveDateProvider", dataProviderClass = InternDataProviders.class)
    void TC_INT_044_valid_dated_leave_request_test(String date, String reason, String desc){
        db.submitLeaveRequest(date,reason,desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        Assert.assertTrue(db.isRowCreated(date));
    }

    @Test( dataProvider = "randomPastLeaveDateProvider", dataProviderClass = InternDataProviders.class)
    void TC_INT_045_invalid_past_dated_leave_request_test(String date, String reason, String desc){
        db.submitLeaveRequest(date,reason,desc);
        Assert.assertFalse(db.isLeaveSubmitted());
        Assert.assertFalse(db.isRowCreated(date));
    }

    @Test(dataProvider = "validLeaveDateProvider", dataProviderClass = InternDataProviders.class)
    void TC_INT_046_duplicate_dated_leave_request_test(String date, String reason, String desc) throws InterruptedException {
        db.submitLeaveRequest(date,reason,desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        Assert.assertTrue(db.isRowCreated(date));
        //submitting multiple requests
        db.refreshPage();
        db.submitLeaveRequest(date,reason,desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        db.refreshPage();
        if (db.getCountOfLeaveRequestsOnDate(date)>1){
            Assert.fail();
        }
        else{
            Assert.assertTrue(true);
        }

    }






}
