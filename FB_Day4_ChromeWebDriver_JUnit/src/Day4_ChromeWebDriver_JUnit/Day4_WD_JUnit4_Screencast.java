package Day4_ChromeWebDriver_JUnit;

//import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class Day4_WD_JUnit4_Screencast {

	String WebDriverBrowPath = "C:/Selenium/chromedriver.exe";
	String WebDriverType = "webdriver.chrome.driver";
	WebDriver oWD;
	
	//Declare and Initialize the variables
	String EmailAddress = "TestingLearner31@gmail.com";
	String Password = "Abcd12345";
	String VerifyPassword = "Abcd12345";
	String DisplayName = "Learner131";
	String Country = "Argentina";
	
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void DriverSript() throws Exception{
		
		try {
				for(int i=1; i<=3; i++){
					test_NewAccount();
					test_Login();
				} //Closing of for loop
		} //closing of try block
		
		catch(Exception e){
			e.printStackTrace();
		} //closing of catch block
			
	} //Closing of function : DriverSript()

	
	public void test_NewAccount() {
		
		try{

			System.setProperty(WebDriverType, WebDriverBrowPath);
			WebDriver oWD;
			
			oWD = new ChromeDriver();
			oWD.get("http://www.screencast.com");
			
			oWD.manage().window().maximize();
			oWD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			// Enter Email Address
			oWD.findElement(By.id("email")).sendKeys(EmailAddress);
			
			//Enter Password
			oWD.findElement(By.id("password1")).sendKeys(Password);
			
			//Enter Verify Password
			oWD.findElement(By.id("password2")).sendKeys(VerifyPassword);
			
			//Enter Display Name
			oWD.findElement(By.xpath("//*[@id='displayName']")).sendKeys(DisplayName);
			
			//Select Country
			new Select(oWD.findElement(By.id("countrySelect"))).selectByVisibleText(Country);
			
			//Click on SignUp Button
			oWD.findElement(By.className("signup")).click();
			
			//Capture the Confirmation Message
			String ConfMessage = oWD.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
			
			//Validate the Confirmation Message
			//if(ConfMessage == "Thank you for creating a Screencast.com account!"){
			if(ConfMessage.equals("Thank you for creating a Screencast.com account!")){
				System.out.println("Account Created. Test Passed. The message that we captured is : " + ConfMessage);
			} else{
				System.out.println("Account is not Created. Test Failed. The message that we captured is : " + ConfMessage);
			}
			
			//Close the Browser
			oWD.quit();			
			
		} //Closing of try block
		
		catch(Exception e){
			e.printStackTrace();
		} //closing of catch block

	} //Closing of function : test_NewAccount()


	public void test_Login() throws Exception {
		
		try {

			System.setProperty(WebDriverType, WebDriverBrowPath);
			oWD = new ChromeDriver();
			oWD.get("http://www.screencast.com");
			
			oWD.manage().window().maximize();
			oWD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			oWD.findElement(By.xpath("//*[@id='emailAddress']")).sendKeys(EmailAddress);
			oWD.findElement(By.xpath("//*[@id='password']")).sendKeys(Password);
			oWD.findElement(By.id("signInButton")).click();
			Thread.sleep(3000);
			String ConfMessage2 = oWD.findElement(By.id("signedInAs")).getText();
			String ExpMsg = "Signed in as " + DisplayName + "(Sign Out)";
			if(ConfMessage2.equals(ExpMsg)){
				System.out.println("Successfully logged in. Test Passed. The Conf Msg is : " + ConfMessage2);
			} else{
				System.out.println("Not logged in. Test Failed. The Conf Msg is : " + ConfMessage2);
			}
			
			//Close the Browser
			oWD.quit();
			
		} //Closing of try block

		catch(Exception e){
			e.printStackTrace();
		} //closing of catch block	
		
	} //Closing of function : test_Login()
	
	@After
	public void tearDown() throws Exception {
		//oWD.quit();
	}
	
} //Closing of Class
