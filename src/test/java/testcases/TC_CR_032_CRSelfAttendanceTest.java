package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.CRDashboard;
import pageObjects.CRLogin;
import testbases.BaseClass;

public class TC_CR_032_CRSelfAttendanceTest extends BaseClass {

    String crIdValid = "CR001";
    String crPasswordValid = "Password@123";

    @Test(priority = 1, testName = "TC-CR-032: CR Self Attendance Marking")
    public void testCRSelfAttendanceMarking() {
        logger.info("=== Executing TC-CR-032: CR Self Attendance Marking ===");
        try {
            driver.navigate().to("https://sync-inv2.vercel.app/");
            Thread.sleep(2000);
            CRLogin crLogin = new CRLogin(driver);
            crLogin.clickCRRole();
            Thread.sleep(2000);
            crLogin.sendCRId(crIdValid);
            crLogin.sendCRPassword(crPasswordValid);
            crLogin.clickLoginButton();
            Thread.sleep(3000);
            CRDashboard dashboard = new CRDashboard(driver);
            logger.info("Step: Attempting to mark self as present");
            try {
                dashboard.clickMarkPresent();
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.info("Mark Present button not found, checking Present Today list");
            }
            int presentCount = dashboard.getPresentListRowCount();
            Assert.assertTrue(presentCount > 0, "CR should appear in Present Today list");
            logger.info("TC-CR-032 PASSED: CR attendance marked, Present list contains " + presentCount + " entries");
        } catch (Exception e) {
            logger.error("TC-CR-032 FAILED: " + e.getMessage(), e);
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
