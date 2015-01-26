package com.PracticeAfterCompleting5;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This test is for testing sample website for selenium practicing
 * @author MWaseem
 *
 */

public class PracticeAfterCompleting5 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.toolsqa.com/automation-practice-form");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//First Syntax
		//driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("FrstNme");
		
		//Second Syntax
		driver.findElement(By.cssSelector("input[name='firstname'][type = 'text']")).sendKeys("FrstNme");
		
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("LstNme");
		List<WebElement> genderradiogroup = driver.findElements(By.cssSelector("input[name='sex']"));
		int sizeofgenderradiobutton = genderradiogroup.size();
		System.out.println("Size of Gender Radio Button is : " + sizeofgenderradiobutton);
		
		// Check which gender is selected by default. Select the one which is not selected by default
		if(genderradiogroup.get(0).isSelected()){
			System.out.println("Male is selected by default. So selecting Female");
			genderradiogroup.get(1).click();
		} else {
			System.out.println("Female is selected by default. So selecting Male");
			genderradiogroup.get(0).click();
		}
		
		//Loop through the radio group to find the suitable Experience Radio Button
		int flag = 0;
		List<WebElement> experienceradiogroup = driver.findElements(By.cssSelector("input[name='exp']"));
		int sizeofexperienceradiogroup = experienceradiogroup.size();
		System.out.println("Size of Experience Radio Group is : " + sizeofexperienceradiogroup);
		for(int i=0; i<sizeofexperienceradiogroup; i++) {
			String checkexperienceradiovalue = experienceradiogroup.get(i).getAttribute("value");
			if(checkexperienceradiovalue.contains("5")){
				experienceradiogroup.get(i).click();
				flag = 1;
				break;
			} else if(i == sizeofexperienceradiogroup && flag == 0) {
				System.out.println("Radio Button with value = '5' is not found");
			}
		}
		
		driver.findElement(By.cssSelector("input[id='datepicker']")).sendKeys("02/01/2015");
		
		//Loop through the checkbox group to find the suitable Profession CheckBox
		flag = 0;
		List<WebElement> professioncheckboxgroup = driver.findElements(By.cssSelector("input[name='profession']"));
		int sizeofprofessioncheckboxgroup = professioncheckboxgroup.size();
		System.out.println("Size of Profession CheckBox Group is : " + sizeofprofessioncheckboxgroup);
		for(int i=0; i<sizeofprofessioncheckboxgroup; i++) {
			String checkprofession = professioncheckboxgroup.get(i).getAttribute("value");
			if (checkprofession.contains("Automation Tester")) {
				professioncheckboxgroup.get(i).click();
				flag = 1;
				break;
			} else if(i == sizeofprofessioncheckboxgroup && flag == 0) {
				System.out.println("CheckBox with value = 'Automation Tester'is not found");
			}
		}
		
		//Click on Link Test Link
		driver.findElement(By.cssSelector("a[title='Automation Practice Table']")).click();
		
		
		//Learn more cssSelector at : "https://saucelabs.com/resources/selenium/css-selectors"
		
		//Regular Expression
//		CSS in Selenium has an interesting feature of allowing partial string matches using ^=, $=, or *=. I’ll define them, then show an example of each:
//
//			^= Match a prefix
//
//			css=a[id^='id_prefix_']
//			A link with an “id” that starts with the text “id_prefix_”
//
//			$= Match a suffix
//
//			css=a[id$='_id_sufix']
//			A link with an “id” that ends with the text “_id_sufix”
//
//			*= Match a substring
//
//			css=a[id*='id_pattern']
//			A link with an “id” that contains the text “id_pattern”
//
//			Matching by inner text
//			And last, one of the more useful pseudo-classes, :contains() will match elements with the desired text block:
//
//			css=a:contains('Log Out')
//			This will find the log out button on your page no matter where it’s located. This is by far my favorite CSS selector and I find it greatly simplifies a lot of my test code.
		
		
	}

}
