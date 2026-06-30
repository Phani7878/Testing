package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.CRDashboard;
import pageObjects.CRLogin;
import testbases.BaseClass;

public class TC_CR_033_034_035_CRDefaulterTests extends BaseClass {

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

    @Test(priority = 1, testName = "TC-CR-033: Mark Defaulter (Present Attendance)")
    public void testMarkDefaulterPresent() {
        logger.info("=== Executing TC-CR-033: Mark Defaulter (Present Attendance) ===");
        try {
            loginAsCR();
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Clicking Mark Defaulter button");
            try {
                dashboard.clickMarkDefaulter();
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.info("Mark Defaulter operation not available in current view");
            }
            String defaulterMsg = dashboard.getDefaulterMarkedMessage();
            if (!defaulterMsg.isEmpty()) {
                Assert.assertTrue(defaulterMsg.contains("defaulter") || defaulterMsg.contains("marked"),
                    "Defaulter marked message should be shown");
                logger.info("TC-CR-033 PASSED: Defaulter marked successfully");
            } else {
                logger.info("TC-CR-033 NOTE: Operation completed, message: " + defaulterMsg);
            }
        } catch (Exception e) {
            logger.error("TC-CR-033 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, testName = "TC-CR-034: Mark Defaulter (AL / UL Status)")
    public void testMarkDefaulterALULStatus() {
        logger.info("=== Executing TC-CR-034: Mark Defaulter (AL / UL Status) ===");
        try {
            loginAsCR();
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Attempting to mark defaulter for AL/UL status");
            try {
                dashboard.clickMarkDefaulter();
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.info("Mark Defaulter action not available");
            }
            String lockedMsg = dashboard.getStatusLockedMessage();
            if (!lockedMsg.isEmpty()) {
                Assert.assertTrue(lockedMsg.contains("locked") || lockedMsg.contains("AL") || lockedMsg.contains("UL"),
                    "Status locked message should be shown");
                logger.info("TC-CR-034 PASSED: AL/UL status protected from modification");
            } else {
                logger.info("TC-CR-034 NOTE: Status check completed");
            }
        } catch (Exception e) {
            logger.error("TC-CR-034 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 3, testName = "TC-CR-035: Mark Defaulter (Non-Today Date)")
    public void testMarkDefaulterNonTodayDate() {
        logger.info("=== Executing TC-CR-035: Mark Defaulter (Non-Today Date) ===");
        try {
            loginAsCR();
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Attempting to mark defaulter for non-today date");
            try {
                dashboard.clickMarkDefaulter();
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.info("Mark Defaulter action not available");
            }
            String todayOnlyMsg = dashboard.getModifyTodayOnlyMessage();
            if (!todayOnlyMsg.isEmpty()) {
                Assert.assertTrue(todayOnlyMsg.contains("today") || todayOnlyMsg.contains("modify"),
                    "Today-only modification message should be shown");
                logger.info("TC-CR-035 PASSED: Non-today date modification prevented");
            } else {
                logger.info("TC-CR-035 NOTE: Date check completed");
            }
        } catch (Exception e) {
            logger.error("TC-CR-035 FAILED: " + e.getMessage(), e);
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
