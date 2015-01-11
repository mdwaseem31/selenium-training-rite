package Package1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver103 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.screencast.com");
		
		try{
			Thread.sleep(500);
		}catch(InterruptedException ie){
			System.out.println("Error Occured");
			ie.printStackTrace();
		}
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("Testgmail133223456@gmail.com");
		
		WebElement password1 = driver.findElement(By.id("password1"));
		password1.sendKeys("Test12345");
		
		driver.findElement(By.id("password2")).sendKeys("Test12345");
		
		driver.findElement(By.id("displayName")).sendKeys("TestingUser43312345");
		
		driver.findElement(By.id("signUpButton")).click();
		
		//Boolean check1 = driver.findElements(By.cssSelector("BODY")).contains("Thank you for creating a Screencast.com account!");
		Boolean check1 = driver.findElement(By.cssSelector("BODY")).getText().contains("Thank you for creating a Screencast.com account!");
		
		if(check1){
			System.out.println("Element Present. Test Passed");
		}
			else{
				System.out.println("Element is not present. Test Failed");
				}
		driver.quit();
		}
	}

