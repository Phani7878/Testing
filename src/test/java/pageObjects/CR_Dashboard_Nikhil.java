package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BasePage;

import java.time.Duration;

public class CR_Dashboard_Nikhil extends BasePage {

    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait quickwait;
    public CR_Dashboard_Nikhil(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        quickwait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
     @FindBy(xpath = "//p[contains(text(),'Token:')]")
    WebElement tokenElement;

    @FindBy(xpath = "//p[contains(text(),'No token yet')]")
    WebElement tokenNotFoundElement;

    @FindBy(xpath = "//button[contains(text(),'Generate Token')]")
    WebElement generateTokenBtn;

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    WebElement logoutBtn;

    public void logout(){
//        System.out.println("LogoutCalled");
        logoutBtn.click();
    }
   void wait_for_token(){
        wait.until(ExpectedConditions.visibilityOf(tokenElement));
    }

    public String getTokenGenerateIfNot(){
        try{
            quickwait.until(ExpectedConditions.elementToBeClickable(generateTokenBtn));
            generateTokenBtn.click();
        }
        catch(Exception e){
            System.out.println("Already generated");
        }
        return getToken();
    }
     String getToken(){

        wait_for_token();
        String content = tokenElement.getText().trim();
        String token = content.substring(7);
        return token;



    }
}
