package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;

public class CRLogin extends BAsePage {
    WebDriver driver;
    WebDriverWait wait;

    public CRLogin(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//body/app-root/app-role-selection/div[@class='min-h-screen bg-gradient-to-br from-slate-50 to-slate-100 flex flex-col items-center justify-center px-4 py-12 font-sans']/div[@class='grid grid-cols-1 md:grid-cols-2 gap-8 w-full max-w-3xl']/div[3]/div[1]")
    WebElement crRole;

    @FindBy(xpath = "//input[@placeholder='e.g. EMP001']")
    WebElement crId;

    @FindBy(xpath = "//input[@placeholder='••••••••']")
    WebElement crPassword;

    @FindBy(xpath = "//button[@class='w-full mt-8 bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 active:from-blue-800 active:to-blue-900 text-white font-semibold py-3 rounded-xl transition-all duration-200 shadow-lg hover:shadow-xl hover:-translate-y-0.5 active:shadow-md active:translate-y-0 flex items-center justify-center gap-2']")
    WebElement loginButton;

    @FindBy(xpath = "//p[@class='text-sm font-medium text-red-900']")
    WebElement errorMessage;

    @FindBy(xpath = "//h1[contains(text(), 'CR Dashboard')]")
    WebElement crDashboard;

    @FindBy(xpath = "//a[contains(text(), 'Generate Token')]")
    WebElement generateTokenLink;

    @FindBy(xpath = "//a[contains(text(), 'Present Today List')]")
    WebElement presentTodayListLink;

    @FindBy(xpath = "//a[contains(text(), 'Daily Analytics')]")
    WebElement dailyAnalyticsLink;

    @FindBy(xpath = "//a[contains(text(), 'Attendance Sheet')]")
    WebElement attendanceSheetLink;

    @FindBy(xpath = "//button[contains(text(),'🚪 Logout')]")
    WebElement crLogout;

    public void clickCRRole() {
        wait.until(ExpectedConditions.elementToBeClickable(crRole));
        crRole.click();
    }

    public void sendCRId(String id) {
        wait.until(ExpectedConditions.visibilityOf(crId)).clear();
        crId.sendKeys(id);
    }

    public void sendCRPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(crPassword)).clear();
        crPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getCRDashboardTitle() {
        wait.until(ExpectedConditions.visibilityOf(crDashboard));
        return crDashboard.getText();
    }

    public boolean isDashboardDisplayed() {
        try {
            return crDashboard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGenerateTokenLinkVisible() {
        try {
            return generateTokenLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresentTodayListLinkVisible() {
        try {
            return presentTodayListLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDailyAnalyticsLinkVisible() {
        try {
            return dailyAnalyticsLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAttendanceSheetLinkVisible() {
        try {
            return attendanceSheetLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickGenerateToken() {
        wait.until(ExpectedConditions.elementToBeClickable(generateTokenLink));
        generateTokenLink.click();
    }

    public void clickPresentTodayList() {
        wait.until(ExpectedConditions.elementToBeClickable(presentTodayListLink));
        presentTodayListLink.click();
    }

    public void clickDailyAnalytics() {
        wait.until(ExpectedConditions.elementToBeClickable(dailyAnalyticsLink));
        dailyAnalyticsLink.click();
    }

    public void clickAttendanceSheet() {
        wait.until(ExpectedConditions.elementToBeClickable(attendanceSheetLink));
        attendanceSheetLink.click();
    }

    public void crLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(crLogout));
        crLogout.click();
    }
}
