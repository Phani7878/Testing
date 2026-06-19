package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;

public class UserLogin extends BAsePage {
    WebDriver driver;
    WebDriverWait wait;
    public UserLogin(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath="//body/app-root/app-role-selection/div[@class='min-h-screen bg-gradient-to-br from-slate-50 to-slate-100 flex flex-col items-center justify-center px-4 py-12 font-sans']/div[@class='grid grid-cols-1 md:grid-cols-2 gap-8 w-full max-w-3xl']/div[2]/div[1]")
    WebElement userlogin;
    @FindBy(xpath = "//input[@placeholder='e.g. EMP001']")
    WebElement userId;
    @FindBy(xpath ="//input[@placeholder='••••••••']")
    WebElement userPwd;
    @FindBy(xpath = "//button[@class='w-full mt-8 bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 active:from-blue-800 active:to-blue-900 text-white font-semibold py-3 rounded-xl transition-all duration-200 shadow-lg hover:shadow-xl hover:-translate-y-0.5 active:shadow-md active:translate-y-0 flex items-center justify-center gap-2']")
    WebElement login;
    public void clickUser(){
        userlogin.click();
    }
    public void sendUserName(String userName){
        userId.sendKeys(userName);
    }
    public void sendUserPassword(String pwd){
        userPwd.sendKeys(pwd);
    }
    public void clickUserLoginButton(){
        login.click();
    }
}
