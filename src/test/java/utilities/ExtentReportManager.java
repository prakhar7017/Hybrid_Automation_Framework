package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.awt.Desktop;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter; // #Configuration -> this is used to define the file path, report name, and some configuration options like theme, timeline, charts, etc.
    public ExtentReports extent; //  This is the main object that manages the test reports.
    public ExtentTest test; //Represents a single test case in the report.

    String repName;

    public void onStart(ITestContext testContext){
//        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//        Date dt= new Date();
//
//        String currentDateTimestamp=df.format(dt);

        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName="Test-Report-"+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter("reports\\"+repName);

        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);


        extent= new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        String os=testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("operating system",os);
        String browser=testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("operating system",browser);

        List<String>includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includeGroups.isEmpty()){
            extent.setSystemInfo("Groups",includeGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display the groups
        test.log(Status.PASS,result.getName()+" got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+" got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {

        extent.flush();

        // for automatically opening the report
        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }


		/*  try {
			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

		  // Create the email message
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com"); //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
		  email.attach(url, "extent report", "please check report...");
		  email.send(); // send the email
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  }
		 */

    }
}
