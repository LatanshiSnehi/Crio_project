package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;





public class Wrappers {
     WebDriver driver;
      WebDriverWait wait;

     String url = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";

     @FindBy(xpath = "//input[@aria-labelledby='i1']")
     private WebElement nameInput;

     @FindBy(xpath = "//textarea[@aria-label ='Your answer']")
     private WebElement whyPraticeAutomationTextarea;

     @FindBy(xpath = "//div//span[contains (text() ,'0 - 2') or contains (text() ,'3 - 5') or contains (text() ,'6 - 10') or contains (text() ,'> 10')] ")
     private List<WebElement> radioBtns ;

     @FindBy(xpath = "//span[contains(@class, 'n5vBHf')]")
     private List<WebElement> checkBoxes;

     @FindBy(xpath = "//div/span[contains (text() , 'Choose')]")
     private WebElement chooseBtn;

     @FindBy(xpath = "(//span[contains(text(), 'Mr')])[3]")
     private WebElement mrElement;

     @FindBy(xpath = "(//span[contains(text(), 'Mrs')])[2]")
     private WebElement mrsElement;

    @FindBy(xpath = "(//span[contains(text(), 'Ms')])[2]")
    private WebElement msElement;

    @FindBy(xpath = "(//span[contains(text(), 'Dr')])[2]")
    private WebElement drElement;

    @FindBy(xpath = "(//span[contains(text(), 'Rather not say')])[1]")
    private WebElement ratherNotSay;

    


     @FindBy(xpath = "//input[@type ='date']")
     private WebElement dateField;

     @FindBy(xpath = "(//input[contains(@class, 'whsOnd') and contains(@class, 'zHQkBf')])[3]")
     private WebElement hoursField;
     
     @FindBy(xpath = "(//input[contains(@class, 'whsOnd') and contains(@class, 'zHQkBf')])[4]")
     private WebElement minutesField;

     @FindBy(xpath = "(//span[contains (@class , 'l4V7wb Fxmcue')])[1]")
     private WebElement submitBtn;

     @FindBy(className = "vHW8K")
     private WebElement successMsg;




     

     public Wrappers(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

      //   AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(driver, this);

      //   allGenders = Arrays.asList(mrElement, mrsElement, msElement, drElement, ratherNotSay);
         
     }

     public void navigateToForm() throws InterruptedException{
        driver.get(url);
        Thread.sleep(2000);
     }

     public void enterName(String name){
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
       
        nameInput.sendKeys(name);
     }

     public void enterAnswer(){
        wait.until(ExpectedConditions.visibilityOf(whyPraticeAutomationTextarea));
        whyPraticeAutomationTextarea.clear();
        String s = "I want to be the best QA Engineer! ";
        long epochTime = System.currentTimeMillis() / 1000;
        String answer = s+epochTime; 
        whyPraticeAutomationTextarea.sendKeys(answer);
     }

     public void selectRadioBtn(String option) throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfAllElements(radioBtns));
        for(int i =0; i<radioBtns.size(); i++){
            if(radioBtns.get(i).getText().equalsIgnoreCase(option)){
               Thread.sleep(3000);
                radioBtns.get(i).click();
                break;
            }
        }
     }

     public void selectCheckBox(String label) throws InterruptedException{
      
      for(WebElement checkBox : checkBoxes){
         if(checkBox.getText().equals(label)){
           
            wait.until(ExpectedConditions.elementToBeClickable(checkBox));
            checkBox.click();
            break;
         }
      }
     }

      public void selectGender(String gender) throws InterruptedException {
            Thread.sleep(2000);
            chooseBtn.click();

   
            if (gender.equalsIgnoreCase("Mr")) {
                  wait.until(ExpectedConditions.visibilityOf(mrElement)).click();
            } else if (gender.equalsIgnoreCase("Mrs")) {
                  wait.until(ExpectedConditions.visibilityOf(mrsElement)).click();
            } else if (gender.equalsIgnoreCase("Ms")) {
                  wait.until(ExpectedConditions.visibilityOf(msElement)).click();
            } else if (gender.equalsIgnoreCase("Dr")) {
                  wait.until(ExpectedConditions.visibilityOf(drElement)).click();
            } else if (gender.equalsIgnoreCase("Rather not say")) {
                  wait.until(ExpectedConditions.visibilityOf(ratherNotSay)).click();
            } else {
                  System.out.println("Gender option not found: " + gender);
               }
            }
           
       

     public void enterDate(int daysBefore){
         LocalDate currentDate = LocalDate.now();
        LocalDate dateSevenDaysBefore = currentDate.minusDays(daysBefore);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateSevenDaysBefore.format(formatter);

        wait.until(ExpectedConditions.visibilityOf(dateField));

        dateField.clear();
        dateField.sendKeys(formattedDate);


     }

     public void enterTime(String hrs , String min){
      wait.until(ExpectedConditions.visibilityOf(hoursField));
      hoursField.clear();
      hoursField.sendKeys(hrs);
      wait.until(ExpectedConditions.visibilityOf(minutesField));
      minutesField.clear();
      minutesField.sendKeys(min);


     }

     public void submit(){
      wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

      wait.until(ExpectedConditions.visibilityOf(successMsg));
      System.out.println("Success msg : "+ successMsg.getText());
     }








    
}
