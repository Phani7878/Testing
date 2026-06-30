package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;
import java.util.List;

public class CRDashboard extends BAsePage {
    WebDriver driver;
    WebDriverWait wait;

    public CRDashboard(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Token Generation Page Elements
    @FindBy(xpath = "//button[contains(text(), 'Generate Token')]")
    WebElement generateTokenButton;

    @FindBy(xpath = "//p[contains(text(), 'Token already generated for today')]")
    WebElement tokenAlreadyGeneratedMsg;

    @FindBy(xpath = "//p[contains(text(), 'Success')]")
    WebElement successMessage;

    @FindBy(xpath = "//div[@class='token-display']//p")
    WebElement tokenDisplay;

    @FindBy(xpath = "//button[contains(text(), 'Get Today Token')]")
    WebElement getTodayTokenButton;

    @FindBy(xpath = "//div[@class='token-info']//p")
    WebElement todayTokenInfo;

    // Present Today List Elements
    @FindBy(xpath = "//table[@class='present-list']//tbody//tr")
    List<WebElement> presentListRows;

    @FindBy(xpath = "//button[contains(text(), 'Mark Defaulter')]")
    WebElement markDefaulterButton;

    @FindBy(xpath = "//span[contains(text(), 'Marked as defaulter')]")
    WebElement defaulterMarkedMsg;

    @FindBy(xpath = "//span[contains(text(), 'Cannot modify: status is locked')]")
    WebElement statusLockedMsg;

    @FindBy(xpath = "//span[contains(text(), 'Can only modify today\\'s attendance')]")
    WebElement modifyTodayOnlyMsg;

    // Daily Analytics Elements
    @FindBy(xpath = "//table[@class='daily-analytics']//tbody//tr")
    List<WebElement> analyticsRows;

    @FindBy(xpath = "//th[contains(text(), 'Emp ID')]")
    WebElement empIdHeader;

    @FindBy(xpath = "//th[contains(text(), 'Name')]")
    WebElement nameHeader;

    @FindBy(xpath = "//th[contains(text(), 'Attendance Status')]")
    WebElement statusHeader;

    // Attendance Sheet Elements
    @FindBy(xpath = "//input[@placeholder='From Date']")
    WebElement fromDateInput;

    @FindBy(xpath = "//input[@placeholder='To Date']")
    WebElement toDateInput;

    @FindBy(xpath = "//button[contains(text(), 'View Attendance')]")
    WebElement viewAttendanceButton;

    @FindBy(xpath = "//table[@class='attendance-matrix']")
    WebElement attendanceMatrix;

    @FindBy(xpath = "//span[@class='attendance-present']")
    List<WebElement> presentMarkers;

    @FindBy(xpath = "//span[@class='attendance-absent']")
    List<WebElement> absentMarkers;

    // Self Attendance Elements
    @FindBy(xpath = "//button[contains(text(), 'Mark Present')]")
    WebElement markPresentButton;

    @FindBy(xpath = "//span[contains(text(), 'Self attendance marked')]")
    WebElement selfAttendanceMarkedMsg;

    // Helper methods for token operations
    public void clickGenerateToken() {
        wait.until(ExpectedConditions.elementToBeClickable(generateTokenButton));
        generateTokenButton.click();
    }

    public String getTokenAlreadyGeneratedMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(tokenAlreadyGeneratedMsg));
            return tokenAlreadyGeneratedMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getGeneratedToken() {
        try {
            wait.until(ExpectedConditions.visibilityOf(tokenDisplay));
            return tokenDisplay.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickGetTodayToken() {
        wait.until(ExpectedConditions.elementToBeClickable(getTodayTokenButton));
        getTodayTokenButton.click();
    }

    public String getTodayTokenInfo() {
        try {
            wait.until(ExpectedConditions.visibilityOf(todayTokenInfo));
            return todayTokenInfo.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Helper methods for attendance
    public int getPresentListRowCount() {
        try {
            return presentListRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickMarkDefaulter() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(markDefaulterButton));
            markDefaulterButton.click();
        } catch (Exception e) {
            // Element may not exist
        }
    }

    public String getDefaulterMarkedMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(defaulterMarkedMsg));
            return defaulterMarkedMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStatusLockedMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(statusLockedMsg));
            return statusLockedMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getModifyTodayOnlyMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(modifyTodayOnlyMsg));
            return modifyTodayOnlyMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Helper methods for daily analytics
    public int getDailyAnalyticsRowCount() {
        try {
            return analyticsRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickDailyAnalytics() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Daily Analytics')]")));
            driver.findElement(By.xpath("//a[contains(text(), 'Daily Analytics')]"))
                    .click();
        } catch (Exception e) {
            // Fallback: try clicking a header or any visible link/button
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Analytics')]")));
                driver.findElement(By.xpath("//a[contains(., 'Analytics')]")).click();
            } catch (Exception ex) {
                // no-op
            }
        }
    }

    public boolean isEmpIdHeaderVisible() {
        try {
            return empIdHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNameHeaderVisible() {
        try {
            return nameHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStatusHeaderVisible() {
        try {
            return statusHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Helper methods for attendance sheet
    public void setFromDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(fromDateInput));
        fromDateInput.clear();
        fromDateInput.sendKeys(date);
    }

    public void setToDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(toDateInput));
        toDateInput.clear();
        toDateInput.sendKeys(date);
    }

    public void clickViewAttendance() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAttendanceButton));
        viewAttendanceButton.click();
    }

    public boolean isAttendanceMatrixDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(attendanceMatrix)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getPresentMarkerCount() {
        try {
            return presentMarkers.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getAbsentMarkerCount() {
        try {
            return absentMarkers.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Helper method for self attendance
    public void clickMarkPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(markPresentButton));
            markPresentButton.click();
        } catch (Exception e) {
            // Element may not exist
        }
    }

    public String getSelfAttendanceMarkedMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(selfAttendanceMarkedMsg));
            return selfAttendanceMarkedMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickAttendanceSheet() {
        try {
            // Primary: click the Attendance Sheet link on dashboard
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Attendance Sheet')]")));
            driver.findElement(By.xpath("//a[contains(text(), 'Attendance Sheet')]"))
                    .click();
        } catch (Exception e) {
            // Fallback: try clicking a button or navigating via viewAttendanceButton if present
            try {
                wait.until(ExpectedConditions.elementToBeClickable(viewAttendanceButton));
                viewAttendanceButton.click();
            } catch (Exception ex) {
                // No-op: element not available; leave method gracefully
            }
        }
    }
}
