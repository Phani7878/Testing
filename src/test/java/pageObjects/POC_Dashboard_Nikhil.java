package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BasePage;

import java.time.Duration;
import java.util.List;

public class POC_Dashboard_Nikhil extends BasePage {
    WebDriverWait wait;

    public POC_Dashboard_Nikhil(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
    }

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    WebElement logoutBtn;

    @FindBy(xpath = "//div[@id='leaves']/h2/following-sibling::div[contains(text(),'for')]")
    WebElement actionAck;

    @FindBy(xpath = "//td[text() = 'INTQEASD007']/following-sibling::td//button")
    WebElement openCohort;

    @FindBy(xpath = "//button[contains(text(),'Onboard Intern')]")
    WebElement onboardBtn;

    @FindBy(xpath = "//label[contains(text(),'Employee ID')]/following-sibling::input")
    WebElement empIDInput;

    @FindBy(xpath ="//label[contains(text(),'Full Name')]/following-sibling::input")
    WebElement fullNameInput;

    @FindBy(xpath = "//label[contains(text(),'Email')]/following-sibling::input")
    WebElement emailInput;

    @FindBy(xpath = "//label[contains(text(),'Mobile')]/following-sibling::input")
    WebElement mobileInput;

    @FindBy(xpath = "//button[normalize-space()='Onboard']")
    WebElement submitOnboardBtn;


    @FindBy(xpath = "//div[contains(text(),'onboarded!')]")
    WebElement onboardSuccessMsg;

    @FindBy(xpath = "//div[contains(text(),'Temp')]/strong")
    WebElement tempPassword;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    WebElement closeButton;

    public void logout(){
//        System.out.println("LogoutCalled");
        logoutBtn.click();
    }


    public void approveLeave(String name,String date,String reason,String desc){
        date = date.trim();
        String[] arr = date.split("-");
        date = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//td[contains(text(),'"+name+"')]/following-sibling::td[text()='"+date+"']/following-sibling::td[text()='"+reason+"']/following-sibling::td[text()='"+desc+"']/following-sibling::td//button[text()='Approve']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement approveBtn = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
        approveBtn.click();
        wait.until(ExpectedConditions.visibilityOf(actionAck));
    }

    public void rejectLeave(String name,String date,String reason,String desc){
        date = date.trim();
        String[] arr = date.split("-");
        date = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//td[contains(text(),'"+name+"')]/following-sibling::td[text()='"+date+"']/following-sibling::td[text()='"+reason+"']/following-sibling::td[text()='"+desc+"']/following-sibling::td//button[text()='Reject']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement approveBtn = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
        approveBtn.click();
        wait.until(ExpectedConditions.visibilityOf(actionAck));
    }

    public void markUnapprovedLeave(String name,String date,String reason,String desc){
        date = date.trim();
        String[] arr = date.split("-");
        date = arr[2]+"-"+arr[1]+"-"+arr[0];
        String xpath = "//td[contains(text(),'"+name+"')]/following-sibling::td[text()='"+date+"']/following-sibling::td[text()='"+reason+"']/following-sibling::td[text()='"+desc+"']/following-sibling::td//button[text()='Mark UL']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement approveBtn = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
        approveBtn.click();
        wait.until(ExpectedConditions.visibilityOf(actionAck));
    }

    public String OnboardNewInternAndReturnTempPassword(String id, String fullName, String email,String mobile){
        wait.until(ExpectedConditions.visibilityOf(openCohort));
        openCohort.click();
        wait.until(ExpectedConditions.elementToBeClickable(onboardBtn));
        onboardBtn.click();

        empIDInput.clear();
        empIDInput.sendKeys(id);
        fullNameInput.clear();
        fullNameInput.sendKeys(fullName);
        emailInput.clear();
        emailInput.sendKeys(email);
        mobileInput.clear();
        mobileInput.sendKeys(mobile);

        submitOnboardBtn.click();
        wait.until(ExpectedConditions.visibilityOf(onboardSuccessMsg));
        wait.until(ExpectedConditions.visibilityOf(tempPassword));
        String res=tempPassword.getText();
        closeButton.click();
        System.out.println(id);
        System.out.println(res);

        return res;


    }


}
