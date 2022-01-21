package framework.managers;

import framework.utils.PropsConst;
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
        System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConst.PATH_CHROM_DRIVER_WINDOWS));
        driver = new ChromeDriver();

    }
    public void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}