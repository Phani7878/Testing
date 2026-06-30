package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.CRDashboard;
import pageObjects.CRLogin;
import testbases.BaseClass;

public class TC_CR_029_030_031_CRTokenTests extends BaseClass {

    String crIdValid = "CR001";
    String crPasswordValid = "Password@123";

    private void loginAsCR() throws InterruptedException {
        driver.navigate().to("https://sync-inv2.vercel.app/");
        Thread.sleep(2000);
        CRLogin crLogin = new CRLogin(driver);
        crLogin.clickCRRole();
        Thread.sleep(2000);
        crLogin.sendCRId(crIdValid);
        crLogin.sendCRPassword(crPasswordValid);
        crLogin.clickLoginButton();
        Thread.sleep(3000);
    }

    @Test(priority = 1, testName = "TC-CR-029: Generate Token (First Time Today)")
    public void testGenerateTokenFirstTime() {
        logger.info("=== Executing TC-CR-029: Generate Token (First Time Today) ===");
        try {
            loginAsCR();
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Clicking Generate Token button");
            dashboard.clickGenerateToken();
            Thread.sleep(2000);
            String token = dashboard.getGeneratedToken();
            Assert.assertNotNull(token, "Token should be generated");
            Assert.assertFalse(token.isEmpty(), "Token should not be empty");
            String successMsg = dashboard.getSuccessMessage();
            Assert.assertNotNull(successMsg, "Success message should be displayed");
            Assert.assertTrue(token.length() > 0, "Token value should be displayed");
            logger.info("TC-CR-029 PASSED: Token generated successfully: " + token);
        } catch (Exception e) {
            logger.error("TC-CR-029 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, testName = "TC-CR-030: Generate Token (Already Generated Today)", dependsOnMethods = "testGenerateTokenFirstTime")
    public void testGenerateTokenAlreadyGenerated() {
        logger.info("=== Executing TC-CR-030: Generate Token (Already Generated Today) ===");
        try {
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Clicking Generate Token button again");
            dashboard.clickGenerateToken();
            Thread.sleep(2000);
            String token = dashboard.getGeneratedToken();
            Assert.assertNotNull(token, "Existing token should be displayed");
            String alreadyGenMsg = dashboard.getTokenAlreadyGeneratedMessage();
            Assert.assertTrue(alreadyGenMsg.contains("already generated") || alreadyGenMsg.contains("already"),
                "Message indicating token already generated should be shown");
            logger.info("TC-CR-030 PASSED: Token already generated message displayed");
        } catch (Exception e) {
            logger.error("TC-CR-030 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 3, testName = "TC-CR-031: View Today's Token", dependsOnMethods = "testGenerateTokenAlreadyGenerated")
    public void testViewTodaysToken() {
        logger.info("=== Executing TC-CR-031: View Today's Token ===");
        try {
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Clicking Get Today Token button");
            dashboard.clickGetTodayToken();
            Thread.sleep(2000);
            String tokenInfo = dashboard.getTodayTokenInfo();
            Assert.assertNotNull(tokenInfo, "Token info should be displayed");
            Assert.assertFalse(tokenInfo.isEmpty(), "Token info should not be empty");
            logger.info("TC-CR-031 PASSED: Today's token displayed: " + tokenInfo);
        } catch (Exception e) {
            logger.error("TC-CR-031 FAILED: " + e.getMessage(), e);
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
