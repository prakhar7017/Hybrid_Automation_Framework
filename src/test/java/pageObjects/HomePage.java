package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;

    @FindBy(linkText = "Login")
    WebElement linkLogin;

    public void clickMyAccount(){
        try{
            wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        } catch (Exception e) {
            System.out.println("Exception while clicking my account: " + e.getMessage());
        }

    }

    public void clickRegister(){
        try{
            wait.until(ExpectedConditions.visibilityOf(register)).click();
        } catch (Exception e) {
            System.out.println("Exception while clicking register: " + e.getMessage());
        }

    }

    public void clickLogin(){
        wait.until(ExpectedConditions.visibilityOf(linkLogin)).click();
    }
}
