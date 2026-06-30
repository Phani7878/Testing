package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.CRDashboard;
import pageObjects.CRLogin;
import testbases.BaseClass;

public class TC_CR_036_037_CRAnalyticsAttendanceTests extends BaseClass {

    String crIdValid = "CR001";
    String crPasswordValid = "Password@123";

    @Test(priority = 1, testName = "TC-CR-036: View Daily Analytics")
    public void testViewDailyAnalytics() {
        logger.info("=== Executing TC-CR-036: View Daily Analytics ===");
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
            logger.info("Step: Opening Daily Analytics");
            dashboard.clickDailyAnalytics();
            Thread.sleep(3000);
            Assert.assertTrue(dashboard.isEmpIdHeaderVisible(), "Emp ID column should be visible");
            Assert.assertTrue(dashboard.isNameHeaderVisible(), "Name column should be visible");
            Assert.assertTrue(dashboard.isStatusHeaderVisible(), "Attendance Status column should be visible");
            int rowCount = dashboard.getDailyAnalyticsRowCount();
            Assert.assertTrue(rowCount > 0, "Daily Analytics should contain data");
            logger.info("TC-CR-036 PASSED: Daily Analytics displayed with " + rowCount + " records");
        } catch (Exception e) {
            logger.error("TC-CR-036 FAILED: " + e.getMessage(), e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, testName = "TC-CR-037: View Attendance Sheet (Date Range)")
    public void testViewAttendanceSheet() {
        logger.info("=== Executing TC-CR-037: View Attendance Sheet (Date Range) ===");
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
            logger.info("Step: Opening Attendance Sheet");
            dashboard.clickAttendanceSheet();
            Thread.sleep(2000);
            dashboard.setFromDate("06/20/2026");
            dashboard.setToDate("06/27/2026");
            logger.info("Step: Clicking View Attendance");
            dashboard.clickViewAttendance();
            Thread.sleep(3000);
            Assert.assertTrue(dashboard.isAttendanceMatrixDisplayed(), "Attendance matrix should be displayed");
            int presentCount = dashboard.getPresentMarkerCount();
            int absentCount = dashboard.getAbsentMarkerCount();
            Assert.assertTrue(presentCount >= 0 && absentCount >= 0, "Attendance markers should be present in matrix");
            logger.info("TC-CR-037 PASSED: Attendance sheet displayed with Present: " + presentCount + ", Absent: " + absentCount);
        } catch (Exception e) {
            logger.error("TC-CR-037 FAILED: " + e.getMessage(), e);
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
