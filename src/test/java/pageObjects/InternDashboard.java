package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BasePage;

import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Collections;

public class InternDashboard extends BasePage {


    WebDriverWait wait;
    public InternDashboard(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "/html/body/app-root/app-layout/div/aside/div[1]/div/p")
    WebElement internTitle;

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    WebElement logoutBtn;

    @FindBy(xpath = "/html/body/app-root/app-layout/div/div/main/app-intern-dashboard/div/div[1]/div/div[2]/p[2]")
    WebElement attendanceStatus;

    @FindBy(xpath = "//input[@placeholder='Paste token here']")
    WebElement tokenInputBox;

    @FindBy(xpath = "//button[contains(text(),'Punch In')]")
    WebElement punchInBtn;

    @FindBy(xpath = "//div[contains(text(),'Invalid QR token')]")
    WebElement invalidTokenMsg;

    @FindBy(xpath = "//p[contains(text(),'You have already punched in today')]")
    WebElement alreadyPunchedInMsg;

    @FindBy(xpath = "//input[@type = 'date']")
    WebElement leaveDateInput;

    @FindBy(xpath = "//input[@placeholder = 'Medical / Personal']")
    WebElement leaveReasonInput;

    @FindBy(xpath = "//input[@placeholder = 'Short note']")
    WebElement leaveDescInput;

    @FindBy(xpath = "//button[contains(text(),'Submit Leave')]")
    WebElement leaveSubmitBtn;

    @FindBy(xpath = "//*[contains(text() ,'Leave request submitted')]")
    WebElement leaveRequestSuccessMsg;

    @FindBy(xpath = "//p/span")
    WebElement internName;

    public void waitTillTitleLoads(){
        wait.until(ExpectedConditions.visibilityOf(internTitle));
    }
    public void waitTillPunchInBtnLoads(){
        wait.until(ExpectedConditions.elementToBeClickable(punchInBtn));
    }
    public void waitTillTokenInputLoads(){
        wait.until(ExpectedConditions.visibilityOf(tokenInputBox));
    }



    public String getTitleText(){

        String res = internTitle.getText().toLowerCase();
        System.out.println(res);
        return res;
    }
    public void logout(){
//        System.out.println("LogoutCalled");
        logoutBtn.click();
    }

    public void waitTillStatusTurnsPresent(){
        wait.until(ExpectedConditions.textToBePresentInElement(attendanceStatus,"PRESENT"));
    }

    public String getAttendanceStatus(){

        wait.until(ExpectedConditions.visibilityOf(attendanceStatus));
        return attendanceStatus.getText().toLowerCase();
    }
    public void sendTokenInput(String token){
        waitTillTokenInputLoads();
        tokenInputBox.clear();
        tokenInputBox.sendKeys(token);
    }
    public void clickPunchIn(){
        waitTillPunchInBtnLoads();
        punchInBtn.click();
    }
    public void waitTillInvalidTokenMsgAppears(){
        wait.until(ExpectedConditions.visibilityOf(invalidTokenMsg));
    }
    public String isAlreadyPunchedIn(){
        wait.until(ExpectedConditions.visibilityOf(alreadyPunchedInMsg));
        return getAttendanceStatus();
    }

    void sendDate(String date){
        wait.until(ExpectedConditions.visibilityOf(leaveDateInput));

        leaveDateInput.clear();
        leaveDateInput.sendKeys(date);
//        wait.until(ExpectedConditions.attributeContains(leaveDateInput,"value",date));
    }

    void sendReason(String reason){
        wait.until(ExpectedConditions.visibilityOf(leaveReasonInput));

        leaveReasonInput.clear();
        leaveReasonInput.sendKeys(reason);
        wait.until(ExpectedConditions.attributeContains(leaveReasonInput,"value",reason));
    }

    void sendDescription(String desc){
        wait.until(ExpectedConditions.visibilityOf(leaveDescInput));

        leaveDescInput.clear();
        leaveDescInput.sendKeys(desc);
        wait.until(ExpectedConditions.attributeContains(leaveDescInput,"value",desc));
    }

    public void submitLeaveRequest(String date,String reason, String desc){
        sendDate(date);
        sendReason(reason);
        sendDescription(desc);
        wait.until(ExpectedConditions.elementToBeClickable(leaveSubmitBtn));
        leaveSubmitBtn.click();
    }

    public boolean isLeaveSubmitted(){

        wait.until(ExpectedConditions.visibilityOf(leaveRequestSuccessMsg));

        return true;
    }
    public boolean isRowCreated(String date){
        date = date.trim();
        String[] arr = date.split("-");
        String newdate = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//table//tr/td[text() = '"+newdate+"']";
        WebElement row;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return true;
    }


    public int getCountOfLeaveRequestsOnDate(String date) {

        date = date.trim();
        String[] arr = date.split("-");
        date = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//table//tr/td[text() = '"+date+"']";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        return driver.findElements(By.xpath(xpath)).size();

    }
    public String getLeaveStatus(String date, String reason,String desc){
        date = date.trim();
        String[] arr = date.split("-");
        date = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//td[text()='"+date+"']/following-sibling::td[text()='"+reason+"']/following-sibling::td[text()='"+desc+"']/following-sibling::td";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement status = driver.findElement(By.xpath(xpath));

        return status.getText().toLowerCase();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getInternName(){
        wait.until(ExpectedConditions.visibilityOf(internName));
        return internName.getText();
    }
}
