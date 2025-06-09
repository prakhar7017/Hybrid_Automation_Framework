package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void setEmail(String _email){
        wait.until(ExpectedConditions.visibilityOf(txtEmailAddress)).sendKeys(_email);
    }
    public void setPass(String _password){
        wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(_password);
    }
    public void clickLoginBtn(){
        wait.until(ExpectedConditions.visibilityOf(btnLogin)).click();
    }
}