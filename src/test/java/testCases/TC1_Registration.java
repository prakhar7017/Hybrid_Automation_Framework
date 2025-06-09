package testCases;


import org.testng.Assert;

import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;


public class TC1_Registration extends BaseClass {
    @Test()
    public void verify_account_registration(){
        try{
            LOGGER.info("********** Starting TC1_Registration **********");
            HomePage hp=new HomePage(driver,wait);
            hp.clickMyAccount();
            hp.clickRegister();

            RegisterPage rp= new RegisterPage(driver,wait);
            rp.setFirstName(randomString());
            rp.setLastName(randomString());
            rp.setTelePhone(randomeNumber());
            rp.setEmail(randomString()+"@gmail.com");
            String password=randomeAlphaNumberic();
            rp.setPassword(password);
            rp.setConfirmPassword(password);
            rp.setPrivacyPolicy();
            rp.clickContinue();
            LOGGER.info("Validating excepted messege");
            String cmsg=rp.getConfirmationMsg();
            if(cmsg.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }else {
                LOGGER.error("Test Failed");
                Assert.assertTrue(false);
            }
        }catch(Exception e){
            LOGGER.error("Test Failed->"+e.getMessage());
            Assert.fail();
        }
    }
}
