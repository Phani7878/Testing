package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InternDashboard;
import pageObjects.POC_Dashboard_Nikhil;
import pageObjects.UserLogin;
import testbases.BaseClass;
import java.util.Random;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TS_17_InternLeaveStatusTest extends BaseClass {

    InternDashboard db ;
    UserLogin login_page_obj;
    String intern_full_name = "SAI NAGA BHARGAV PENUGONDA";
    String internUsername = "2494469";
    String internPassword = "123456";
    String pocUsername = "123";
    String pocPassword = "12345678";

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


    @Test()
    void TC_INT_047_leave_status_change_after_POC_accepts() throws InterruptedException {
        clickUser();
        loginAsUser(internUsername, internPassword);

        // Generate unique identifier using random number
        int uniqueId = new Random().nextInt(99999);
        String date = "12-12-2026";
        String reason = "UNIQUE_IDENTIFIER_" + uniqueId;
        String desc = "Request status to be APPROVED_" + uniqueId;

        db.submitLeaveRequest(date, reason, desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        Assert.assertTrue(db.isRowCreated(date));
        db.logout();
        clickUser();
        loginAsUser(pocUsername,pocPassword);
        POC_Dashboard_Nikhil poc_db = new POC_Dashboard_Nikhil(driver);

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


        poc_db.approveLeave(intern_full_name,date,reason,desc);
        poc_db.logout();
        clickUser();
        loginAsUser(internUsername, internPassword);
        try {
            String status = db.getLeaveStatus(date, reason, desc);
            Assert.assertEquals(status.trim(), "approved");
        }
        finally {
            db.logout();
        }

    }


    @Test()
    void TC_INT_048_leave_status_change_after_POC_rejects() throws InterruptedException {
        clickUser();
        loginAsUser(internUsername, internPassword);

        // Generate unique identifier using random number
        int uniqueId = new Random().nextInt(99999);
        String date = "12-12-2026";
        String reason = "UNIQUE_IDENTIFIER_" + uniqueId;
        String desc = "Request status to be REJECTED_" + uniqueId;

        db.submitLeaveRequest(date, reason, desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        Assert.assertTrue(db.isRowCreated(date));
        db.logout();
        clickUser();
        loginAsUser(pocUsername,pocPassword);
        POC_Dashboard_Nikhil poc_db = new POC_Dashboard_Nikhil(driver);

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

        poc_db.rejectLeave(intern_full_name,date,reason,desc);
        poc_db.logout();
        clickUser();

        loginAsUser(internUsername,internPassword);
        try {
            String status = db.getLeaveStatus(date, reason, desc);
            Assert.assertEquals(status.trim(), "rejected");
        }
        finally {
            db.logout();
        }

    }

    @Test()
    void TC_INT_049_leave_status_change_after_POC_marksUL() throws InterruptedException {
        clickUser();
        loginAsUser(internUsername, internPassword);

        // Generate unique identifier using random number
        int uniqueId = new Random().nextInt(99999);
        String date = "12-12-2026";
        String reason = "UNIQUE_IDENTIFIER_" + uniqueId;
        String desc = "Request status to be REJECTED_" + uniqueId;

        db.submitLeaveRequest(date, reason, desc);
        Assert.assertTrue(db.isLeaveSubmitted());
        Assert.assertTrue(db.isRowCreated(date));
        db.logout();
        clickUser();
        loginAsUser(pocUsername,pocPassword);
        POC_Dashboard_Nikhil poc_db = new POC_Dashboard_Nikhil(driver);

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

        poc_db.markUnapprovedLeave(intern_full_name,date,reason,desc);
        poc_db.logout();
        clickUser();

        loginAsUser(internUsername,internPassword);
        try {
            String status = db.getLeaveStatus(date, reason, desc);
            Assert.assertEquals(status.trim(), "unapproved");
        }
        finally {
            db.logout();
        }

    }



}
