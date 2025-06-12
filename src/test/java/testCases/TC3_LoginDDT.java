package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC3_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class) // dataProvider from different class thats why we have to mention like this.
    public void verify_loginDDT(String email,String pass,String exp){
        LOGGER.info("****** Starting TC003_LoginDDT ******");
        try{
            HomePage hp=new HomePage(driver,wait);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp=new LoginPage(driver,wait);
            lp.setEmail(email);
            lp.setPass(pass);
            lp.clickLoginBtn();

            MyAccountPage macc=new MyAccountPage(driver,wait);
            boolean targetPage=macc.isMyAccountPageExist();
            if(exp.equalsIgnoreCase("Valid")){
                // valid
                if(targetPage){
                    macc.clickLogout();
                    Assert.assertTrue(true);
                }else{
                    Assert.fail();

                }
            }else{
                // invalid
                if (targetPage){
                    macc.clickLogout();
                    Assert.fail();

                }else{
                    Assert.assertTrue(true);
                }
            }
        }catch(Exception e){
            LOGGER.info("error in login test->"+e.getMessage());
            Assert.fail();
        }
        LOGGER.info("****** Finished TC002_LoginTest ******");
    }
}
