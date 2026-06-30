package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.InternDashboard;
import pageObjects.UserLogin;
import testbases.BaseClass;

public class TS_14_InternLoginTest extends BaseClass {
    UserLogin login_page_obj;
    @BeforeMethod
    void clickUser(){
        login_page_obj = new UserLogin(driver);
        login_page_obj.clickUser();
    }



    @Test(dataProvider = "validInternLoginData", dataProviderClass = utilities.InternDataProviders.class)
    void TC_INT_039_correct_credential_login_test(String username,String password) throws InterruptedException {
        login_page_obj.sendUserName(username);
        login_page_obj.sendUserPassword(password);
        login_page_obj.clickUserLoginButton();
        InternDashboard db = new InternDashboard(driver);
        db.waitTillTitleLoads();
        String titleText = db.getTitleText();
        Assert.assertEquals(titleText,"intern dashboard");
        db.logout();

    }
    @Test( dataProvider = "invalidInternLoginData", dataProviderClass = utilities.InternDataProviders.class)
    void TC_INT_040_invalid_credential_login_test(String username,String password) throws InterruptedException {


        login_page_obj.sendUserName(username);
        login_page_obj.sendUserPassword(password);
        login_page_obj.clickUserLoginButton();
        login_page_obj.waitTillErrorDisplayed();
        String errorMsg = login_page_obj.getErrorMessage();
        try{
            if(errorMsg.equalsIgnoreCase("login failed")){
                Assert.assertTrue(true);
            }
            else{
                Assert.fail();
            }
        }
        finally {
            login_page_obj.gotoHome();
        }

    }


}
