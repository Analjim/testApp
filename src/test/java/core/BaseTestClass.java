package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.io.*;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.ITestResult;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

public class BaseTestClass {

        public static String baseUrl;
        public static String remote;
        public static String remoteBrowser;
	    //protected static DesiredCapabilities capabilities;
        public static String remotePlatform;
        public static String localBrowser;
        public static String remotePath;
	    //protected static URL url;
	    public static String username;
	    public static String password;
	    private String screenshotsPath;
	    private Properties props;
	    public static String prjRoot;
	    protected static String chromeDriver;
	    public WebDriver seleniumDriver;//it uses specific browser driver (e.g. chromeDriver) to automate browser
	    public String testMethodName;
	    public static Logger log;
	    public static int implicitWait;//used in find element selenium method
	    public static int explicitWait;//used in wait object (wait is usually combined with ExpectedCondition class)



	    @BeforeSuite
	    public void suiteBefore(){
            System.out.println("BASETESTCLASS");
	    	//before tests start read all config data
	        //instantiate logger object
	        log = Logger.getLogger("Accruent Tests");
	        //read configuration from properties.config
	        try {
	            props = new Properties();
	            InputStream in = BaseTestClass.class.getClassLoader().getResourceAsStream("config.properties");
	            props.load(in);
	            in.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
            //seleniumDriver = Driver.seleniumDriver;
	        remote = props.getProperty("remote");//Driver.remote;
	        remoteBrowser = props.getProperty("remote.browser");
	        remotePlatform = props.getProperty("remote.platform");//Driver.remotePlatform;
	        remotePath = props.getProperty("remote.path");//Driver.remotePath;
			localBrowser = props.getProperty("local.browser");//Driver.localBrowser;
	        baseUrl = props.getProperty("base.url");//Driver.baseUrl;
	        screenshotsPath = props.getProperty("screenshots");
	        username = props.getProperty("username");
	        password = props.getProperty("password");
	        implicitWait = Integer.parseInt(props.getProperty("implicit.wait"));
	        explicitWait = Integer.parseInt(props.getProperty("explicit.wait"));
	        chromeDriver = props.getProperty("chrome.path");
	        prjRoot = System.getProperty("user.dir");//project root folder
	        System.setProperty("webdriver.chrome.driver", prjRoot + chromeDriver);
	    }
	    
	    @BeforeMethod
	    public void setUp(Method method) {
	    	//each test will get newly created selenium driver instance based on config parameters from above
	        testMethodName = method.getName();
	        if(remote.equals("no"))
	            log.info(this.getClass().getName() + "." +  this.testMethodName + "()" + ", browser = " + localBrowser);
	        else
	            log.info(this.getClass().getName() + "." +  this.testMethodName  + "()" + ", browser = " + remoteBrowser);
	    }
	    
	    @AfterMethod
	    public void tearDown(ITestResult result) {
	        if (!result.isSuccess()) {
	            log.error(this.getClass().getName() + "." +  this.testMethodName + "()" + " failed");
	            takeScreenshot(this.testMethodName);
	        }
	        Driver.seleniumDriver.close();
	    }

	    @AfterSuite
	    public void TestSuiteIsFinished(){
            Driver.seleniumDriver.quit();//close browser when suite is finished
	    }
	    
	    public void takeScreenshot(String failedMethod) {
	        Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("M_d_hhmmssa");
	        File directory = new File("");
	        if (remote.equals("yes"))
	            Driver.seleniumDriver = new Augmenter().augment(Driver.seleniumDriver);
	        final File scrFile = ((TakesScreenshot) Driver.seleniumDriver).getScreenshotAs(OutputType.FILE);
	        log.info("Thread id that failed = " + Thread.currentThread().getId());
	        try {
	            // Save the Screenshot
	            String filePath = getClass().getSimpleName() + "_" + failedMethod
	                    + "_" + ft.format(dNow);
	            String saveAs = directory.getAbsolutePath()
	                    + screenshotsPath + filePath + ".png";
	            FileUtils.copyFile(scrFile, new File(saveAs));
	            log.info("Screenshot saved as: " + saveAs);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
