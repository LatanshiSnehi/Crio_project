package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    ChromeDriver driver;

    Wrappers wrappers ;
   @BeforeTest(enabled = true)
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }


    @Test(description = "Verify functionality of testCase01" , enabled = true)
    public void  testCase01(){
       

    try{
        System.out.println("starting testcase01");
        wrappers = new Wrappers(driver);
        Thread.sleep(2000);
        wrappers.navigateToForm();
        Thread.sleep(2000);
        

        wrappers.enterName("Crio Learner");
        
      
        wrappers.enterAnswer();
        

        wrappers.selectRadioBtn("3 - 5");
        

        wrappers.selectCheckBox("Java");
        
        wrappers.selectCheckBox("Selenium");
        Thread.sleep(5000);

        wrappers.selectGender("Ms");

        Thread.sleep(10000);
        
       wrappers.enterDate(7);
       wrappers.enterTime("7", "30");

       wrappers.submit();
       
       Thread.sleep(4000);




        

        System.out.println("testCase01 passed");
        }catch(Exception e){
            System.out.println("testcase01 failed");
            e.printStackTrace();

        }


    }


    @AfterTest
    public void endTest()
    {
        //driver.close();
        driver.quit();

    }
}