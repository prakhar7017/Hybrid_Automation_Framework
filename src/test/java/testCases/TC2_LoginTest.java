package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC2_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login(){
        LOGGER.info("****** Starting TC002_LoginTest ******");
        try{
            HomePage hp=new HomePage(driver,wait);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp=new LoginPage(driver,wait);
            lp.setEmail(p.getProperty("email"));
            lp.setPass(p.getProperty("password"));
            lp.clickLoginBtn();

            MyAccountPage macc=new MyAccountPage(driver,wait);
            boolean targetPage=macc.isMyAccountPageExist();
            Assert.assertTrue(targetPage);
        }catch(Exception e){
            LOGGER.info("error in login test->"+e.getMessage());
            Assert.fail();
        }
        LOGGER.info("****** Finished TC002_LoginTest ******");
    }
}
