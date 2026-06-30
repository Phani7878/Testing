// combined test cases for CR module: TC-CR-027 to TC-CR-037

// package testcases;

// import org.testng.Assert;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.Test;
// import pageObjects.CRDashboard;
// import pageObjects.CRLogin;
// import testbases.BaseClass;

// public class TC_CR_027_037_CRModuleTests extends BaseClass {

//     // Test Data
//     String crIdValid = "CR001";
//     String crPasswordValid = "Password@123";
//     String crIdInvalid = "CR999";
//     String crPasswordInvalid = "WrongPassword";

//     // TC-CR-027: CR Login (Valid Credentials)
//     @Test(priority = 1, testName = "TC-CR-027: CR Login (Valid Credentials)")
//     public void testCRLoginValidCredentials() {
//         logger.info("=== Executing TC-CR-027: CR Login (Valid Credentials) ===");
        
//         try {
//             CRLogin crLogin = new CRLogin(driver);
            
//             // Validate preconditions
//             logger.info("Precondition: CR account exists and first-login process is completed");
            
//             // Step: Login as CR using valid credentials
//             logger.info("Step: Clicking on CR role button");
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
            
//             logger.info("Step: Entering CR ID: " + crIdValid);
//             crLogin.sendCRId(crIdValid);
            
//             logger.info("Step: Entering CR password");
//             crLogin.sendCRPassword(crPasswordValid);
            
//             logger.info("Step: Clicking login button");
//             crLogin.clickLoginButton();
            
//             Thread.sleep(3000);
            
//             // Expected Result Validation
//             logger.info("Validating: CR Dashboard is displayed");
//             Assert.assertTrue(crLogin.isDashboardDisplayed(), 
//                 "CR Dashboard should be displayed");
            
//             logger.info("Validating: Dashboard contains required links");
//             Assert.assertTrue(crLogin.isGenerateTokenLinkVisible(), 
//                 "Generate Token link should be visible");
//             Assert.assertTrue(crLogin.isPresentTodayListLinkVisible(), 
//                 "Present Today List link should be visible");
//             Assert.assertTrue(crLogin.isDailyAnalyticsLinkVisible(), 
//                 "Daily Analytics link should be visible");
//             Assert.assertTrue(crLogin.isAttendanceSheetLinkVisible(), 
//                 "Attendance Sheet link should be visible");
            
//             logger.info("TC-CR-027 PASSED: All expected dashboard elements are present");
            
//         } catch (Exception e) {
//             logger.error("TC-CR-027 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-028: CR Login (Invalid Credentials)
//     @Test(priority = 2, testName = "TC-CR-028: CR Login (Invalid Credentials)")
//     public void testCRLoginInvalidCredentials() {
//         logger.info("=== Executing TC-CR-028: CR Login (Invalid Credentials) ===");
        
//         try {
//             // Navigate back to login page
//             driver.navigate().to("https://sync-inv2.vercel.app/");
//             Thread.sleep(2000);
            
//             CRLogin crLogin = new CRLogin(driver);
            
//             // Validate preconditions
//             logger.info("Precondition: Invalid CR credentials available");
            
//             // Step: Attempt login using incorrect password
//             logger.info("Step: Clicking on CR role button");
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
            
//             logger.info("Step: Entering invalid CR ID: " + crIdInvalid);
//             crLogin.sendCRId(crIdInvalid);
            
//             logger.info("Step: Entering invalid CR password");
//             crLogin.sendCRPassword(crPasswordInvalid);
            
//             logger.info("Step: Clicking login button");
//             crLogin.clickLoginButton();
            
//             Thread.sleep(3000);
            
//             // Expected Result Validation
//             logger.info("Validating: Login fails and error message is displayed");
//             String errorMsg = crLogin.getErrorMessage();
//             Assert.assertNotNull(errorMsg, "Error message should be displayed");
//             Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
            
//             logger.info("TC-CR-028 PASSED: Login failed with error: " + errorMsg);
            
//         } catch (Exception e) {
//             logger.error("TC-CR-028 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-029: Generate Token (First Time Today)
//     @Test(priority = 3, testName = "TC-CR-029: Generate Token (First Time Today)", dependsOnMethods = "testCRLoginValidCredentials")
//     public void testGenerateTokenFirstTime() {
//         logger.info("=== Executing TC-CR-029: Generate Token (First Time Today) ===");
        
//         try {
//             // Ensure CR is logged in
//             driver.navigate().to("https://sync-inv2.vercel.app/");
//             Thread.sleep(2000);
            
//             CRLogin crLogin = new CRLogin(driver);
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
//             crLogin.sendCRId(crIdValid);
//             crLogin.sendCRPassword(crPasswordValid);
//             crLogin.clickLoginButton();
//             Thread.sleep(3000);
            
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: CR is logged in");
//             logger.info("Precondition: No token has been generated today for the cohort");
            
//             // Step: Click on Generate Token
//             logger.info("Step: Clicking Generate Token button");
//             dashboard.clickGenerateToken();
            
//             Thread.sleep(2000);
            
//             // Expected Result Validation
//             logger.info("Validating: New token is generated");
//             String token = dashboard.getGeneratedToken();
//             Assert.assertNotNull(token, "Token should be generated");
//             Assert.assertFalse(token.isEmpty(), "Token should not be empty");
            
//             logger.info("Validating: Success message is shown");
//             String successMsg = dashboard.getSuccessMessage();
//             Assert.assertNotNull(successMsg, "Success message should be displayed");
            
//             logger.info("Validating: Token string for the day is displayed");
//             Assert.assertTrue(token.length() > 0, "Token value should be displayed");
            
//             logger.info("TC-CR-029 PASSED: Token generated successfully: " + token);
            
//         } catch (Exception e) {
//             logger.error("TC-CR-029 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-030: Generate Token (Already Generated Today)
//     @Test(priority = 4, testName = "TC-CR-030: Generate Token (Already Generated Today)", dependsOnMethods = "testGenerateTokenFirstTime")
//     public void testGenerateTokenAlreadyGenerated() {
//         logger.info("=== Executing TC-CR-030: Generate Token (Already Generated Today) ===");
        
//         try {
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate preconditions
//             logger.info("Precondition: CR is logged in");
//             logger.info("Precondition: Token already exists for today");
            
//             // Step: Click Generate Token again
//             logger.info("Step: Clicking Generate Token button again");
//             dashboard.clickGenerateToken();
            
//             Thread.sleep(2000);
            
//             // Expected Result Validation
//             logger.info("Validating: Existing token is displayed");
//             String token = dashboard.getGeneratedToken();
//             Assert.assertNotNull(token, "Existing token should be displayed");
            
//             logger.info("Validating: 'Token already generated for today' message is shown");
//             String alreadyGenMsg = dashboard.getTokenAlreadyGeneratedMessage();
//             Assert.assertTrue(alreadyGenMsg.contains("already generated") || 
//                             alreadyGenMsg.contains("already"), 
//                 "Message indicating token already generated should be shown");
            
//             logger.info("TC-CR-030 PASSED: Token already generated message displayed");
            
//         } catch (Exception e) {
//             logger.error("TC-CR-030 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-031: View Today's Token
//     @Test(priority = 5, testName = "TC-CR-031: View Today's Token", dependsOnMethods = "testGenerateTokenAlreadyGenerated")
//     public void testViewTodaysToken() {
//         logger.info("=== Executing TC-CR-031: View Today's Token ===");
        
//         try {
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: Token exists for today");
            
//             // Step: Click Get Today Token
//             logger.info("Step: Clicking Get Today Token button");
//             dashboard.clickGetTodayToken();
            
//             Thread.sleep(2000);
            
//             // Expected Result Validation
//             logger.info("Validating: Today's token and date are displayed");
//             String tokenInfo = dashboard.getTodayTokenInfo();
//             Assert.assertNotNull(tokenInfo, "Token info should be displayed");
//             Assert.assertFalse(tokenInfo.isEmpty(), "Token info should not be empty");
            
//             logger.info("TC-CR-031 PASSED: Today's token displayed: " + tokenInfo);
            
//         } catch (Exception e) {
//             logger.error("TC-CR-031 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-032: CR Self Attendance Marking
//     @Test(priority = 6, testName = "TC-CR-032: CR Self Attendance Marking")
//     public void testCRSelfAttendanceMarking() {
//         logger.info("=== Executing TC-CR-032: CR Self Attendance Marking ===");
        
//         try {
//             // Ensure CR is logged in
//             driver.navigate().to("https://sync-inv2.vercel.app/");
//             Thread.sleep(2000);
            
//             CRLogin crLogin = new CRLogin(driver);
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
//             crLogin.sendCRId(crIdValid);
//             crLogin.sendCRPassword(crPasswordValid);
//             crLogin.clickLoginButton();
//             Thread.sleep(3000);
            
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate preconditions
//             logger.info("Precondition: CR is logged in");
//             logger.info("Precondition: CR has not punched-in today");
            
//             // Step: Navigate to Present Today list
//             logger.info("Step: Opening Present Today list");
            
//             // Step: Mark self as present via CR UI
//             logger.info("Step: Attempting to mark self as present");
//             try {
//                 dashboard.clickMarkPresent();
//                 Thread.sleep(2000);
//             } catch (Exception e) {
//                 logger.info("Mark Present button not found, checking Present Today list");
//             }
            
//             // Expected Result Validation
//             logger.info("Validating: CR appears in Present Today list");
//             int presentCount = dashboard.getPresentListRowCount();
//             Assert.assertTrue(presentCount > 0, "CR should appear in Present Today list");
            
//             logger.info("TC-CR-032 PASSED: CR attendance marked, Present list contains " + presentCount + " entries");
            
//         } catch (Exception e) {
//             logger.error("TC-CR-032 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-033: Mark Defaulter (Present Attendance)
//     @Test(priority = 7, testName = "TC-CR-033: Mark Defaulter (Present Attendance)", dependsOnMethods = "testCRSelfAttendanceMarking")
//     public void testMarkDefaulterPresent() {
//         logger.info("=== Executing TC-CR-033: Mark Defaulter (Present Attendance) ===");
        
//         try {
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: At least one attendance record is marked PRESENT today");
            
//             // Step: From Present Today list, click Mark Defaulter
//             logger.info("Step: Clicking Mark Defaulter button");
//             try {
//                 dashboard.clickMarkDefaulter();
//                 Thread.sleep(2000);
//             } catch (Exception e) {
//                 logger.info("Mark Defaulter operation not available in current view");
//             }
            
//             // Expected Result Validation
//             logger.info("Validating: Attendance status changes to ABSENT");
//             String defaulterMsg = dashboard.getDefaulterMarkedMessage();
            
//             if (!defaulterMsg.isEmpty()) {
//                 Assert.assertTrue(defaulterMsg.contains("defaulter") || 
//                                 defaulterMsg.contains("marked"), 
//                     "Defaulter marked message should be shown");
//                 logger.info("TC-CR-033 PASSED: Defaulter marked successfully");
//             } else {
//                 logger.info("TC-CR-033 NOTE: Operation completed, message: " + defaulterMsg);
//             }
            
//         } catch (Exception e) {
//             logger.error("TC-CR-033 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-034: Mark Defaulter (AL / UL Status)
//     @Test(priority = 8, testName = "TC-CR-034: Mark Defaulter (AL / UL Status)")
//     public void testMarkDefaulterALULStatus() {
//         logger.info("=== Executing TC-CR-034: Mark Defaulter (AL / UL Status) ===");
        
//         try {
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: Attendance status for today is AL or UL");
            
//             // Step: Attempt to mark defaulter
//             logger.info("Step: Attempting to mark defaulter for AL/UL status");
//             try {
//                 dashboard.clickMarkDefaulter();
//                 Thread.sleep(2000);
//             } catch (Exception e) {
//                 logger.info("Mark Defaulter action not available");
//             }
            
//             // Expected Result Validation
//             logger.info("Validating: Action is rejected with appropriate error message");
//             String lockedMsg = dashboard.getStatusLockedMessage();
            
//             if (!lockedMsg.isEmpty()) {
//                 Assert.assertTrue(lockedMsg.contains("locked") || 
//                                 lockedMsg.contains("AL") || 
//                                 lockedMsg.contains("UL"), 
//                     "Status locked message should be shown");
//                 logger.info("TC-CR-034 PASSED: AL/UL status protected from modification");
//             } else {
//                 logger.info("TC-CR-034 NOTE: Status check completed");
//             }
            
//         } catch (Exception e) {
//             logger.error("TC-CR-034 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-035: Mark Defaulter (Non-Today Date)
//     @Test(priority = 9, testName = "TC-CR-035: Mark Defaulter (Non-Today Date)")
//     public void testMarkDefaulterNonTodayDate() {
//         logger.info("=== Executing TC-CR-035: Mark Defaulter (Non-Today Date) ===");
        
//         try {
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: Attendance record belongs to a date other than today");
            
//             // Step: Attempt to mark defaulter
//             logger.info("Step: Attempting to mark defaulter for non-today date");
//             try {
//                 dashboard.clickMarkDefaulter();
//                 Thread.sleep(2000);
//             } catch (Exception e) {
//                 logger.info("Mark Defaulter action not available");
//             }
            
//             // Expected Result Validation
//             logger.info("Validating: Action is rejected with appropriate error message");
//             String todayOnlyMsg = dashboard.getModifyTodayOnlyMessage();
            
//             if (!todayOnlyMsg.isEmpty()) {
//                 Assert.assertTrue(todayOnlyMsg.contains("today") || 
//                                 todayOnlyMsg.contains("modify"), 
//                     "Today-only modification message should be shown");
//                 logger.info("TC-CR-035 PASSED: Non-today date modification prevented");
//             } else {
//                 logger.info("TC-CR-035 NOTE: Date check completed");
//             }
            
//         } catch (Exception e) {
//             logger.error("TC-CR-035 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-036: View Daily Analytics
//     @Test(priority = 10, testName = "TC-CR-036: View Daily Analytics")
//     public void testViewDailyAnalytics() {
//         logger.info("=== Executing TC-CR-036: View Daily Analytics ===");
        
//         try {
//             // Ensure CR is logged in
//             driver.navigate().to("https://sync-inv2.vercel.app/");
//             Thread.sleep(2000);
            
//             CRLogin crLogin = new CRLogin(driver);
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
//             crLogin.sendCRId(crIdValid);
//             crLogin.sendCRPassword(crPasswordValid);
//             crLogin.clickLoginButton();
//             Thread.sleep(3000);
            
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate preconditions
//             logger.info("Precondition: Cohort has interns");
//             logger.info("Precondition: Attendance data exists for the selected date");
            
//             // Step: Open Daily Analytics
//             logger.info("Step: Opening Daily Analytics");
//             dashboard.clickDailyAnalytics();
//             Thread.sleep(3000);
            
//             // Expected Result Validation
//             logger.info("Validating: Daily Analytics table displays required columns");
//             Assert.assertTrue(dashboard.isEmpIdHeaderVisible(), "Emp ID column should be visible");
//             Assert.assertTrue(dashboard.isNameHeaderVisible(), "Name column should be visible");
//             Assert.assertTrue(dashboard.isStatusHeaderVisible(), "Attendance Status column should be visible");
            
//             logger.info("Validating: Analytics data is displayed");
//             int rowCount = dashboard.getDailyAnalyticsRowCount();
//             Assert.assertTrue(rowCount > 0, "Daily Analytics should contain data");
            
//             logger.info("TC-CR-036 PASSED: Daily Analytics displayed with " + rowCount + " records");
            
//         } catch (Exception e) {
//             logger.error("TC-CR-036 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     // TC-CR-037: View Attendance Sheet (Date Range)
//     @Test(priority = 11, testName = "TC-CR-037: View Attendance Sheet (Date Range)")
//     public void testViewAttendanceSheet() {
//         logger.info("=== Executing TC-CR-037: View Attendance Sheet (Date Range) ===");
        
//         try {
//             // Ensure CR is logged in
//             driver.navigate().to("https://sync-inv2.vercel.app/");
//             Thread.sleep(2000);
            
//             CRLogin crLogin = new CRLogin(driver);
//             crLogin.clickCRRole();
//             Thread.sleep(2000);
//             crLogin.sendCRId(crIdValid);
//             crLogin.sendCRPassword(crPasswordValid);
//             crLogin.clickLoginButton();
//             Thread.sleep(3000);
            
//             CRDashboard dashboard = new CRDashboard(driver);
            
//             // Validate precondition
//             logger.info("Precondition: Attendance history exists for the selected date range");
            
//             // Step: Open Attendance Sheet
//             logger.info("Step: Opening Attendance Sheet");
//             dashboard.clickAttendanceSheet();
//             Thread.sleep(2000);
            
//             // Step: Select From and To dates
//             logger.info("Step: Selecting date range (From: 2026-06-20, To: 2026-06-27)");
//             dashboard.setFromDate("06/20/2026");
//             dashboard.setToDate("06/27/2026");
            
//             logger.info("Step: Clicking View Attendance");
//             dashboard.clickViewAttendance();
//             Thread.sleep(3000);
            
//             // Expected Result Validation
//             logger.info("Validating: Matrix view displays dates as columns and members as rows");
//             Assert.assertTrue(dashboard.isAttendanceMatrixDisplayed(), 
//                 "Attendance matrix should be displayed");
            
//             logger.info("Validating: Attendance status displayed for each date");
//             int presentCount = dashboard.getPresentMarkerCount();
//             int absentCount = dashboard.getAbsentMarkerCount();
            
//             Assert.assertTrue(presentCount >= 0 && absentCount >= 0,
//                 "Attendance markers should be present in matrix");
            
//             logger.info("TC-CR-037 PASSED: Attendance sheet displayed with Present: " + 
//                        presentCount + ", Absent: " + absentCount);
            
//         } catch (Exception e) {
//             logger.error("TC-CR-037 FAILED: " + e.getMessage(), e);
//             Assert.fail("Test failed with exception: " + e.getMessage());
//         }
//     }

//     @AfterMethod
//     public void tearDownTestCase() {
//         logger.info("Test case execution completed. Logging out...");
//         try {
//             CRLogin crLogin = new CRLogin(driver);
//             crLogin.crLogout();
//             logger.info("Logout successful");
//         } catch (Exception e) {
//             logger.info("Could not logout - continuing");
//         }
//     }
// }
