package framework.managers;


import java.util.concurrent.TimeUnit;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();
    private  static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework(){
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void quitFramework(){
        driverManager.quitDriver();
    }
}