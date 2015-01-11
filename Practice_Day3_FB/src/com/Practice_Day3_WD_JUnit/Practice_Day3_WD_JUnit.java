package com.Practice_Day3_WD_JUnit;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Practice_Day3_WD_JUnit {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_CreateAccount() {
	
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
		WebDriver oWD = new ChromeDriver();
		oWD.get("http://screencast.com");
		oWD.findElement(By.id("email")).sendKeys("Testingbshdg@gmail.com");
		oWD.findElement(By.id("password1")).sendKeys("Http1234");
		oWD.findElement(By.id("password2")).sendKeys("Http1234");
		oWD.findElement(By.id("displayName")).sendKeys("Httpuser");
		new Select(oWD.findElement(By.id("countrySelect"))).selectByVisibleText("Argentina");
		oWD.findElement(By.id("signUpButton")).click();
		String ConfMsg = oWD.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
		if (ConfMsg == "Thank you for creating a Screencast.com account!"){
			System.out.println("Test Passed. Confirmation Message is : " + ConfMsg);
		} else{
			System.out.println("Test Failed. Confirmation Message that appeared is : " + ConfMsg);
		}
		oWD.quit();
	}

	@Test
	public void test_Login() {
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
