package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telePhone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkedPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String fName){
        try{
            wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
        }catch (Exception e){
            System.out.println("Exception while setting first name: " + e.getMessage());
        }
    }

    public void setLastName(String lName){
        try{
            wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lName);
        } catch (Exception e) {
            System.out.println("Exception while setting last name: " + e.getMessage());
        }
    }

    public void setEmail(String _email){
        try{
            wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(_email);
        } catch (Exception e) {
            System.out.println("Exception while setting email: " + e.getMessage());
        }
    }

    public void setPassword(String _password){
        try{
            wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(_password);
        } catch (Exception e) {
            System.out.println("Exception while setting password: " + e.getMessage());
        }
    }

    public void setTelePhone(String _telephone){
        try{
            wait.until(ExpectedConditions.visibilityOf(telePhone)).sendKeys(_telephone);
        } catch (Exception e) {
            System.out.println("Exception while setting telephone: " + e.getMessage());
        }
    }

    public void setConfirmPassword(String _confirmPassword){
        try{
            wait.until(ExpectedConditions.visibilityOf(confirmPassword)).sendKeys(_confirmPassword);
        } catch (Exception e) {
            System.out.println("Exception while setting confirm password: " + e.getMessage());
        }
    }

    public void setPrivacyPolicy() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkedPolicy)).click();
        } catch (Exception e) {
            System.out.println("Exception while setting privacy policy: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
            // Or use JavaScriptExecutorUtils.clickElement(btnContinue); if necessary
        } catch (Exception e) {
            System.out.println("Exception while clicking Continue: " + e.getMessage());
        }
    }

    public String getConfirmationMsg() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(msgConfirmation)).getText();
        } catch (Exception e) {
            System.out.println("Exception while getting confirmation message: " + e.getMessage());
            return e.getMessage();
        }
    }





}
