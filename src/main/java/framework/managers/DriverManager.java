package framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static DriverManager INSTANCE;
    private WebDriver driver;
    private TestPropManager propManager = TestPropManager.getInstance();

    private DriverManager() {

    }

    public static DriverManager getInstance(){
        if(INSTANCE==null){
            INSTANCE=new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver(){
        if(driver==null){
            initDriver();
        }
        return driver;
    }

    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", propManager.getProperty("path.chrome.driver.windows"));
        driver = new ChromeDriver();

    }
    public void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}