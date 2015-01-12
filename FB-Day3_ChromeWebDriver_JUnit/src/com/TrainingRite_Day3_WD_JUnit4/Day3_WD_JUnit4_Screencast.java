package com.TrainingRite_Day3_WD_JUnit4;

//import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class Day3_WD_JUnit4_Screencast {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void test_NewAccount() {
		
		String WebDriverBrowPath = "C:/Selenium/chromedriver.exe";
		String WebDriverType = "webdriver.chrome.driver";
		System.setProperty(WebDriverType, WebDriverBrowPath);
		WebDriver oWD;
		oWD = new ChromeDriver();
		oWD.get("http://www.screencast.com");
		
		oWD.manage().window().maximize();
		oWD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		oWD.findElement(By.id("email")).sendKeys("testemaildffd1234567@gmail.com");
		oWD.findElement(By.id("password1")).sendKeys("Abcd12345");
		oWD.findElement(By.id("password2")).sendKeys("Abcd12345");
		oWD.findElement(By.xpath("//*[@id='displayName']")).sendKeys("Testinguserdfd12345");
		
		new Select(oWD.findElement(By.id("countrySelect"))).selectByVisibleText("Argentina");
		
		oWD.findElement(By.className("signup")).click();
		
		String ConfMessage = oWD.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
		
		//if(ConfMessage == "Thank you for creating a Screencast.com account!"){
		if(ConfMessage.equals("Thank you for creating a Screencast.com account!")){
			System.out.println("Account Created. Test Passed. The message that we captured is : " + ConfMessage);
		} else{
			System.out.println("Account is not Created. Test Failed. The message that we captured is : " + ConfMessage);
		}
		
		oWD.quit();
	}

	@Test
	public void test_Login() {
		//Some code here--
	}
	
	@After
	public void tearDown() throws Exception {
		//some code here--
	}
	
}
