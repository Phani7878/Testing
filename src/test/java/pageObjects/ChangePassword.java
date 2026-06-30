package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BasePage;

import java.time.Duration;

public class ChangePassword extends BasePage {
    WebDriverWait wait;
    public ChangePassword(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//input[contains(@placeholder,'Enter new password')]")
    WebElement newPassInput;

    @FindBy(xpath = "//input[contains(@placeholder,'Confirm new password')]")
    WebElement confirmPassInput;

    @FindBy(xpath = "//button[contains(text(),'Change Password')]")
    WebElement submitBtn;

    public void setNewPassword(){
        wait.until(ExpectedConditions.visibilityOf(newPassInput));
        newPassInput.clear();
        newPassInput.sendKeys("123456");

        wait.until(ExpectedConditions.visibilityOf(confirmPassInput));
        confirmPassInput.clear();
        confirmPassInput.sendKeys("123456");
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
    }
}
