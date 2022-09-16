package com.atrium.qa.pages;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.atrium.qa.base.TestBase;

//Page wise test cases, this is Login page test cases 

public class LoginPage extends TestBase{

LoginPage loginpage = null;

//Page Factory or Object Repository

@FindBy(name= "username")
WebElement username;

@FindBy(id="password")
WebElement password;

@FindBy(xpath= ".//*[@id='Login']")
WebElement loginBtn;

public LoginPage()
{
PageFactory.initElements(driver, this);
}


//Actions:
public String validateLoginPageTitle(){
return driver.getTitle();
}


public void login(String un,String pwd){
username.sendKeys(un);
password.sendKeys(pwd);
//loginBtn.click();
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", loginBtn);
   
   // String title = loginpage.validateLoginPageTitle();
//  Assert.assertEquals(title, "Login | Salesforce");


try {
   Properties configProperties = new Properties();
   InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config.properties");
   configProperties.load(inputStream);
}
catch(Exception e){
   System.out.println("Could not load the file");
   e.printStackTrace();
}
}
// public boolean validateCRMImage(){
// return crmLogo.isDisplayed();
// }
//
// public HomePage login(String un, String pwd){
// username.sendKeys(un);
// password.sendKeys(pwd);
// //loginBtn.click();
//     JavascriptExecutor js = (JavascriptExecutor)driver;
//     js.executeScript("arguments[0].click();", loginBtn);
//    
// return new HomePage();
// }

}
