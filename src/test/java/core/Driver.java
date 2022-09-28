package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver seleniumDriver;
    //private Properties props;
    public static String remote;//props.getProperty("remote");
    public static String remoteBrowser;//props.getProperty("remote.browser");
    protected static DesiredCapabilities capabilities;
    public static Logger log;//Logger.getLogger("Accruent Tests");
    public static String remotePlatform;
    public static String remotePath;//props.getProperty("remote.path");
    protected static URL url;
    public static String localBrowser;
    private static int implicitWait;//used in find element selenium method
    public static String baseUrl;//props.getProperty("base.url");
    public static String username;
    public static String password;
    public static String prjRoot;
    protected static String chromeDriver;

    public Driver(){
        System.out.println("DRIVER");
        setUp();
        PageFactory.initElements(seleniumDriver, this);
    }

    public WebDriver CreateNewDriver() {//called in setUp method
        seleniumDriver = null;
        try {
        	//local execution
                if (localBrowser.equalsIgnoreCase("IE"))
                    seleniumDriver = new InternetExplorerDriver(new InternetExplorerOptions());
                else if (localBrowser.equalsIgnoreCase("CH")){
                    seleniumDriver = new ChromeDriver(new ChromeOptions());
                } else {
                    log.error("Local browser is not supported! Please check configuration file for browser usage.");
                    throw new IllegalStateException("Local browser selection error!");
                }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        //for created selenium driver (local or remote) set implicit wait period
        seleniumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        return seleniumDriver;
    }

    public void setUp() {
        log = BaseTestClass.log;
        remote = BaseTestClass.remote;//props.getProperty("remote");
        remoteBrowser = BaseTestClass.remoteBrowser;//props.getProperty("remote.browser");
        remotePlatform = BaseTestClass.remotePlatform;//props.getProperty("remote.platform");
        remotePath = BaseTestClass.remotePath;//props.getProperty("remote.path");
        localBrowser = BaseTestClass.localBrowser;//props.getProperty("local.browser");
        baseUrl = BaseTestClass.baseUrl;//props.getProperty("base.url");
        username = BaseTestClass.username;//props.getProperty("username");
        password = BaseTestClass.password;//props.getProperty("password");
        implicitWait = BaseTestClass.implicitWait;//Integer.parseInt(props.getProperty("implicit.wait"));
        chromeDriver = BaseTestClass.chromeDriver;//props.getProperty("chrome.path");
        prjRoot = System.getProperty("user.dir");//project root folder
        System.setProperty("webdriver.chrome.driver", prjRoot + chromeDriver);
        seleniumDriver = CreateNewDriver();
        seleniumDriver.get(baseUrl);//browser is positioned on base application url before a test starts
        seleniumDriver.manage().deleteAllCookies();
        seleniumDriver.manage().window().maximize();
    }
}
