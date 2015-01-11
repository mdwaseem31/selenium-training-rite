package com.Day6_TestNG_Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class JetBlueFFLoginTest {
	
  @Test(dataProvider = "LoginTestDataProvider")
  public void JetBlueLogin(String LoginID, String LoginPassword) {
	  System.out.println("Now executing the Login Test for Email Address: " + LoginID + " whose password is : " + LoginPassword);
	  WebDriver driver = new FirefoxDriver();
	  driver.get("http://www.trainingrite.net");
	  driver.findElement(By.id("txtphone")).sendKeys(LoginID);
	  driver.findElement(By.id("txtpassword")).sendKeys(LoginPassword);
	  driver.findElement(By.id("btnSubmit")).click();
	  driver.quit();
  }

  @DataProvider
  public Object[][] LoginTestDataProvider() {
    return new Object[][] {
      new Object[] { "OnlineLearner431@gmail.com", "Aaaa1111" },
      new Object[] { "OnlineLearner432@gmail.com", "Aaaa1111" },
    };
  }
}
