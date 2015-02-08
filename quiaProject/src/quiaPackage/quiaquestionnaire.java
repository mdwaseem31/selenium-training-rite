package quiaPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This script tests the functionality of quia multiple choice questionnaire
 * 
 * @author WMohammed
 *
 */

public class quiaquestionnaire {

	@Test
	public void test() {
		WebDriver driver = new FirefoxDriver();
	
		//Navigate to quia.com
		driver.get("http://www.quia.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click on "Visit Quia Web" Button
		driver.findElement(By.cssSelector("a[href='/web']")).click();
		
		//Enter User Name
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Saffan007");
		
		//Enter Password
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Aaaa1111");
		
		//Click on Login Button
		driver.findElement(By.cssSelector("input[value = 'Log in']")).click();
		
		//Click on 1st Quizzes Link
		List<WebElement> quizzes = driver.findElements(By.linkText("Quizzes"));
		System.out.println("Number of links with text 'Quizzes' are : " + quizzes.size());
		quizzes.get(0).click();
		
		//Click on "Create a New Quiz" Button
		driver.findElement(By.cssSelector("input[value = 'Create a new quiz']")).click();
	
//		Create an Array
		List listOfExpValuesinAddNumberOfQuesDropodown = Arrays.asList(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"});
		List listOfValuesAppearingInAddNumberOfQuesDropdown = new ArrayList();
		
		//Print all the values in "Add Number of Questions" dropdown
		WebElement addNumberOfQuesDropdown = driver.findElement(By.cssSelector("select[name = 'tagAddNumberOfQuestions']"));
		List<WebElement> addNumberOfQuesDropdownValues = addNumberOfQuesDropdown.findElements(By.tagName("option"));
		int i = 0;
		System.out.println("Printing the values in 'Add Number of Questions' dropdown");
		for(WebElement a:addNumberOfQuesDropdownValues){
			System.out.println(addNumberOfQuesDropdownValues.get(i).getText());
			listOfValuesAppearingInAddNumberOfQuesDropdown.add(a);
			i++;
		}
		System.out.println("End of values in 'Add Number of Questions' dropdown");
	
		//Verify if all the values in "Add Number of Questions" dropdown are correct
		if(listOfValuesAppearingInAddNumberOfQuesDropdown.equals(listOfValuesAppearingInAddNumberOfQuesDropdown)) {
			System.out.println("All the values in 'Add Number of Questions' dropdown are correct");
		} else {
			System.out.println("All the values in 'Add Number of Questions' dropdown are NOT correct");
		}
		
		//Select a value from "Add Number of Questions" dropodown
		Random rand = new Random();
		int r = rand.nextInt(11 - 4 - 1) + 4;
		new Select(addNumberOfQuesDropdown).selectByIndex(r);
		int selectedNumberOfQues = r+1;
		
		//Select a value from "Question Type"
		WebElement addQuesTypeDropdown = driver.findElement(By.cssSelector("select[name = 'tagAddQuestionType']"));
		List<WebElement> addQuesTypeDropdownValues = addQuesTypeDropdown.findElements(By.tagName("option"));
		int j = 0;
		for(WebElement b:addQuesTypeDropdownValues) {
			String checkQuesType = addQuesTypeDropdownValues.get(j).getText();
			if(checkQuesType.equals("multiple choice")) {
				new Select(addQuesTypeDropdown).selectByIndex(j);
				break;
			}
			j++;
		}
		
		//Click on Add Button
		driver.findElement(By.cssSelector("input[name = 'tagAddQuestions0']")).click();
		try{
			Thread.sleep(5000);
		} catch(Exception e) {
			
		}
		
		//Verify the number of questions added by verifying the text
		String verifyNumberOfQuesActual = driver.findElement(By.cssSelector("span[id='question_count_1']")).getText();
		String verifyNumberOfQuesExp = "There are " + selectedNumberOfQues + " questions in this quiz.";
		if(verifyNumberOfQuesActual.equals(verifyNumberOfQuesExp)) {
			System.out.println("The confirmation text pertaining to the number of questions appeared correctly. The text that appeared is : " + verifyNumberOfQuesActual);
		} else {
			System.out.println("The confirmation text pertaining to the number of questions is incorrect. The text that appeared is : " + verifyNumberOfQuesActual);
		}
		

//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("span[id='question_count_1']")).getText(), Integer.toString(r)));
		
		//Enter Title
		driver.findElement(By.cssSelector("input[name='title']")).sendKeys("Practising Multiple Choice Q&A through Selenium");
		
		//Enter Description
		driver.findElement(By.cssSelector("textarea[name='desc']")).sendKeys("Learning to Enter Text, dynamically selecting questions and corresponding answers by parameretizing the properties");
		
		//Enter questions, their corresponding answers and select correct answer
		String quesSelector = "textarea[name^='tagQECPrefixQuestionText_']";
		List<WebElement> listOfQues = driver.findElements(By.cssSelector(quesSelector));
		i = 1;
		for(WebElement ques:listOfQues) {
			ques.sendKeys("This is Question Number : " + i);
			
			//Enter Answers
			String ansSelector = "input[name^='tagQECPrefixAnswerText_" + i + "_" + "']";
			List<WebElement> listOfAns = driver.findElements(By.cssSelector(ansSelector));
			j = 1;
			for(WebElement ans:listOfAns) {
				ans.sendKeys("This is Answer Number : " + j + " for Question Number : " + i);
				j++;
			}
			
			//Select Correct Answer
			r = rand.nextInt(4 - 0 - 1) + 0;
			String correctAnsSelector = "input[value='" + r + "'][name^='tagQECPrefixIsCorrectAnswer_" + i + "']";
			//List<WebElement> listOfCorrectAnsCheckboxes = driver.findElements(By.cssSelector(correctAnsSelector));
			driver.findElement(By.cssSelector(correctAnsSelector)).click();
			
			i++;
		}
		
		//Click on Done Button
		driver.findElement(By.cssSelector("input[name='tagSubmitSave']")).click();
		
		//Quit Driver
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
}
