package selenium102;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver102 {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://www.screencast.com");
		
		/*try{ 
			Thread.sleep(500);
		} catch(InterruptedException ie){
			System.out.println("Error Occured");
			ie.printStackTrace();
		}*/
		
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("testemail13@gmail.com");
		
		WebElement password1 = driver.findElement(By.id("password1"));
		password1.sendKeys("asdfasdf123");
		
		driver.findElement(By.id("password2")).sendKeys("asdfasdf123");
		
		driver.findElement(By.id("displayName")).sendKeys("testemail13");
		
		driver.findElement(By.id("signUpButton")).click();
		
		Boolean check1 = driver.findElement(By.cssSelector("BODY")).getText().contains("Welcome New Guy");
		
		if(check1){
			System.out.println("Element Present. Test complete");
		}else{
			System.out.println("TEst Failed");
		}
		driver.quit();
		
	}

}
