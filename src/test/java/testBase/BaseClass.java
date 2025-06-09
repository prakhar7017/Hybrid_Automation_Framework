package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public WebDriverWait wait;
    public Logger LOGGER = LogManager.getLogger(BaseClass.class);
    public Properties p;

    @BeforeClass()
    @Parameters({"os","browser"})
    public void setup(String os,String browser){
        try {
            FileReader file = new FileReader("src/test/resources/config.properties");
            p = new Properties();
            p.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        switch(browser.toLowerCase()){
            case "chrome": driver=new ChromeDriver(); break;
            case "edge" :  driver=new EdgeDriver(); break;
            case "safari": driver=new SafariDriver(); break;
            default: LOGGER.info("Invalid browser name...."); return;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("url"));
        driver.manage().window().maximize();
    }
    @AfterClass()
    void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedstring= RandomStringUtils.randomAlphabetic(5);
        return generatedstring;
    }

    public String randomeNumber()
    {
        String generatednumber=RandomStringUtils.randomNumeric(10);
        return generatednumber;
    }

    public String randomeAlphaNumberic()
    {
        String generatedstring=RandomStringUtils.randomAlphabetic(3);
        String generatednumber=RandomStringUtils.randomNumeric(3);
        return (generatedstring+"@"+generatednumber);
    }
}
