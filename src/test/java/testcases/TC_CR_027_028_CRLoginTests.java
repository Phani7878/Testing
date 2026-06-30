package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.CRLogin;
import testbases.BaseClass;

public class TC_CR_027_028_CRLoginTests extends BaseClass {

    String crIdValid = "CR001";
    String crPasswordValid = "Password@123";
    String crIdInvalid = "CR999";
    String crPasswordInvalid = "WrongPassword";

    @Test(priority = 1, testName = "TC-CR-027: CR Login (Valid Credentials)")
    public void testCRLoginValidCredentials() {
        logger.info("=== Executing TC-CR-027: CR Login (Valid Credentials) ===");
        try {
            CRLogin crLogin = new CRLogin(driver);
            logger.info("Precondition: CR account exists and first-login process is completed");
            logger.info("Step: Clicking on CR role button");
            crLogin.clickCRRole();
            Thread.sleep(2000);
            logger.info("Step: Entering CR ID: " + crIdValid);
            crLogin.sendCRId(crIdValid);
            logger.info("Step: Entering CR password");
            crLogin.sendCRPassword(crPasswordValid);
            logger.info("Step: Clicking login button");
            crLogin.clickLoginButton();
            Thread.sleep(3000);
            Assert.assertTrue(crLogin.isDashboardDisplayed(), "CR Dashboard should be displayed");
            Assert.assertTrue(crLogin.isGenerateTokenLinkVisible(), "Generate Token link should be visible");
            Assert.assertTrue(crLogin.isPresentTodayListLinkVisible(), "Present Today List link should be visible");
            Assert.assertTrue(crLogin.isDailyAnalyticsLinkVisible(), "Daily Analytics link should be visible");
            Assert.assertTrue(crLogin.isAttendanceSheetLinkVisible(), "Attendance Sheet link should be visible");
            logger.info("TC-CR-027 PASSED: All expected dashboard elements are present");
        } catch (Exception e) {
            logger.error("TC-CR-027 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, testName = "TC-CR-028: CR Login (Invalid Credentials)")
    public void testCRLoginInvalidCredentials() {
        logger.info("=== Executing TC-CR-028: CR Login (Invalid Credentials) ===");
        try {
            driver.navigate().to("https://sync-inv2.vercel.app/");
            Thread.sleep(2000);
            CRLogin crLogin = new CRLogin(driver);
            logger.info("Precondition: Invalid CR credentials available");
            logger.info("Step: Clicking on CR role button");
            crLogin.clickCRRole();
            Thread.sleep(2000);
            logger.info("Step: Entering invalid CR ID: " + crIdInvalid);
            crLogin.sendCRId(crIdInvalid);
            logger.info("Step: Entering invalid CR password");
            crLogin.sendCRPassword(crPasswordInvalid);
            logger.info("Step: Clicking login button");
            crLogin.clickLoginButton();
            Thread.sleep(3000);
            String errorMsg = crLogin.getErrorMessage();
            Assert.assertNotNull(errorMsg, "Error message should be displayed");
            Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
            logger.info("TC-CR-028 PASSED: Login failed with error: " + errorMsg);
        } catch (Exception e) {
            logger.error("TC-CR-028 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            CRLogin crLogin = new CRLogin(driver);
            crLogin.crLogout();
        } catch (Exception e) {
            logger.info("Logout not available after execution");
        }
    }
}
