package com.ScreenCast_SignUp_SignIn;



import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * This test is for testing Screencast SignUp and SignIn functionality
 * @author - WMohammed
 * 
 */

public class ScreenCast_SignUp_SignIn {

	String BrowType;
	String BrowPath;
	String URL;
	String EmailAddress;
	String Password;
	String DisplayName;
	String Country;
	String ExpSignUpConfMsg;
	String ExpSignInConfMsg;
	WebDriver driver;
	String Browser;
	String xlFilePath = "C:/Users/DELL/Documents/Selenium Projects-Workspace/PracticeAfterCompleting2/PracticeAfterCompleting2_RW.xls";
	String xlLocalArray[][];
	int xlNumOfRows, xlNumOfCols;
	
	@Before
	public void setUp() throws Exception {
//		BrowType = "webdriver.chrome.driver";
//		BrowPath = "C:/Selenium/chromedriver.exe";
		URL = "http://screencast.com";
//		EmailAddress = "sdjcfvbj@gmail.com";
//		Password = "Azas1111";
//		DisplayName = "TesterGlmd23";
//		Country = "Argentina";
		ExpSignUpConfMsg = "Thank you for creating a Screencast.com account!";
//		ExpSignInConfMsg = "Signed in as " + DisplayName + "(Sign Out)";
	}
	
	@Test
	public void DriverScript() throws Exception {
		try {
					xlLocalArray = excelRead(xlFilePath);
					for(int i=1; i<xlNumOfRows; i++) {
						System.out.println("Starting to execute iteration : " + i);
						EmailAddress = xlLocalArray[i][0];
						Password = xlLocalArray[i][1];
						DisplayName = xlLocalArray[i][2];
						Country = xlLocalArray[i][3];
						Browser = xlLocalArray[i][4];
						
						SetBrowserType();
						Screencast_SignUp();
						Screencast_SignIn();
				}
			}	catch(Exception e) {
				e.printStackTrace();
			}

	}
	
	public void SetBrowserType() {
		if(Browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(Browser.equals("IE")) {
			System.setProperty("webdriver.IE.driver", "C:/Selenium/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void Screencast_SignUp() {
		
		//System.setProperty(BrowType, BrowPath);
		//driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExpSignInConfMsg = "Signed in as " + DisplayName + "(Sign Out)";
		
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(EmailAddress);
		driver.findElement(By.id("password1")).sendKeys(Password);
		driver.findElement(By.id("password2")).sendKeys(Password);
		driver.findElement(By.id("displayName")).sendKeys(DisplayName);
		//new Select(driver.findElement(By.id("countrySelect")))
		new Select (driver.findElement(By.id("countrySelect"))).selectByVisibleText(Country);
		driver.findElement(By.id("signUpButton")).click();
		String VerifySignUpMsg = driver.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
		if(VerifySignUpMsg.equals(ExpSignUpConfMsg)) {
			System.out.println("Sign Up Test Passed. Message that appeared is : " + VerifySignUpMsg + ". And the Expected Message was : " + ExpSignUpConfMsg);
		} else {
			System.out.println("Sign Up Test Failed. Message that appeared is : " + VerifySignUpMsg + ". And the Expected Message was : " + ExpSignUpConfMsg);
		}
		//driver.quit();
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/table/tbody/tr[1]/td[2]/div/div/span[3]/a")).click();
	}
	

	public void Screencast_SignIn() throws InterruptedException {
//		System.setProperty(BrowType, BrowPath);
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.get(URL);
		Thread.sleep(5000);
		System.out.println("Email Address : " + EmailAddress + " and Password is : " + Password);
		driver.findElement(By.id("emailAddress")).sendKeys(EmailAddress);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.id("signInButton")).click();
		Thread.sleep(5000);
		String VerifySignInMsg = driver.findElement(By.id("signedInAs")).getText();
		if(VerifySignInMsg.equals(ExpSignInConfMsg)) {
			System.out.println("Sign In Test Passed. Message that appeared is : " + VerifySignInMsg + ". And the Expected Message was : " + ExpSignInConfMsg);
		} else {
			System.out.println("Sign In Test Failed. Message that appeared is : " + VerifySignInMsg + ". And the Expected Message was : " + ExpSignInConfMsg);
		}
		driver.quit();
		
	}
	
	public String[][] excelRead(String xlFilePath) throws Exception{
		System.out.println("xlFilePath = " + xlFilePath);
		File fileHandle = new File(xlFilePath);
		FileInputStream testDataStream = new FileInputStream(fileHandle);
		HSSFWorkbook xlWorkbook = new HSSFWorkbook(testDataStream);
		HSSFSheet xlSheet = xlWorkbook.getSheetAt(0);
		xlNumOfRows = xlSheet.getLastRowNum()+1;
		xlNumOfCols = xlSheet.getRow(0).getLastCellNum();
		xlLocalArray = new String[xlNumOfRows][xlNumOfCols];
		
		for (int row = 0; row<xlNumOfRows; row++){
			HSSFRow rowData = xlSheet.getRow(row);
			for (int col = 0; col < xlNumOfCols; col++){
				HSSFCell cell = rowData.getCell(col);//read data from every cell
				String cellvalue = cellToString(cell);
				xlLocalArray[row][col] = cellvalue;
				
			}//end of column loop			
		}//end of row loop
		
		return xlLocalArray;
	} //end of function : excelRead
	
	public static String cellToString(HSSFCell cell){
		//This function will convert an object of type excell cell to string value
		int type = cell.getCellType();
		Object result;
		switch(type){
		case HSSFCell.CELL_TYPE_NUMERIC: //0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING://1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: //2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK://3
			result = "-";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: //4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR://5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type:"+type);
		
		}//end of switch
		return result.toString();
	} //end of function : cellToString

	

	@After
	public void tearDown() throws Exception {
	}


}
