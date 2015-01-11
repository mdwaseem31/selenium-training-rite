package com.Practice_Day4_FB_ChromeWD_JUnit;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Day4_FB_ChromeWD_JUnit {

	String BrowType = "webdriver.chrome.driver";
	String BrowPath = "C:/Selenium/chromedriver.exe";
	WebDriver oWD;
	String URL = "http://www.screencast.com";
	String EmailAddress = "TestingGyu65@gmail.com";
	String Password = "Gggg1234";
	String VerifyPassword = "Gggg1234";
	String DisplayName = "TestingGuy9949";
	String Country = "Argentina";
	
	@Test
	public void DriverScript() {
			
		try {

			for(int i=1; i<=3; i++) {	
				
				test_SignUp();
				test_Login();				
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}


	} //Closing of function : DriverScript
	
	public void test_SignUp() {
		
		String ExpConfMsg1 = "Thank you for creating a Screencast.com account!";

		System.setProperty(BrowType, BrowPath);
		oWD = new ChromeDriver();
		oWD.get(URL);
		oWD.manage().window().maximize();
		oWD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		oWD.findElement(By.id("email")).sendKeys(EmailAddress);
		oWD.findElement(By.id("password1")).sendKeys(Password);
		oWD.findElement(By.id("password2")).sendKeys(VerifyPassword);
		oWD.findElement(By.id("displayName")).sendKeys(DisplayName);
		new Select(oWD.findElement(By.id("countrySelect"))).selectByVisibleText(Country);
		oWD.findElement(By.id("signUpButton")).click();
		String ConfMsg1 = oWD.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
		if(ConfMsg1.equals(ExpConfMsg1)){
			System.out.println("SignUp functionality Passed. Conf Msg that is captured is : " + ExpConfMsg1);
		} else {
			System.out.println("SignUp functionality Failed. Conf Msg that is captured is : " + ExpConfMsg1);
		}
		
		oWD.quit();
		
	} //Closing of function : test_SignUp
	
	public void test_Login() throws InterruptedException {
		
		String ExpConfMsg2 = "Signed in as " + DisplayName + "(Sign Out)";
	
		System.setProperty(BrowType, BrowPath);
		oWD = new ChromeDriver();
		oWD.get(URL);
		oWD.manage().window().maximize();
		oWD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		oWD.findElement(By.id("emailAddress")).sendKeys(EmailAddress);
		oWD.findElement(By.id("password")).sendKeys(Password);
		oWD.findElement(By.id("signInButton")).click();
		Thread.sleep(5000);
		String ConfMsg2 = oWD.findElement(By.id("signedInAs")).getText();
		if(ConfMsg2.equals(ExpConfMsg2)) {
			System.out.println("Successfully Logged in. SignIn functionality Passed. Confirmation message that appeared is : " + ConfMsg2);
		} else {
			System.out.println("Not Logged in. SignIn functionality Failed. Confirmation message that appeared is : " + ConfMsg2);
		}
		oWD.quit();
	}

} //Closing of Class
