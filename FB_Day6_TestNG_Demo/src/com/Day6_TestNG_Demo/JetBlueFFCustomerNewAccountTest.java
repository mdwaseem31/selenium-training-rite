package com.Day6_TestNG_Demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class JetBlueFFCustomerNewAccountTest {

	public WebDriver driver;
	String BrowType;
	String BrowPath;
	int xlNumOfRows, xlNumOfCols;
	String xlLocalArray[][];
	String xlFilePath = "C:/Users/DELL/Documents/Selenium Projects-Workspace/Practice_Day5_FB_DD_Screencast/Practice_Selenium_Day5.xls";
	String url = "http://www.screencast.com";
	String Execute;
	
	String Browser;
	String EmailAddress;
	String Password;
	String VerifyPassword;
	String DisplayName;
	String Country;	
	
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  System.out.println("Executing beforeMethod function of CustomerNewAccount Test");
	  xlLocalArray = excelRead(xlFilePath);
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Executing beforeClass function of CustomerNewAccount Test");
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Executing beforeTest function of CustomerNewAccount Test");
  }
  
  @Test(enabled=false)
  public void CustomerNewAccountForUSA() {
	  System.out.println("Executing the code of CustomerNewAccountForUSA Test.....");
	  
		try {
			
			for(int i=1; i<xlNumOfRows; i++) {
				Execute = xlLocalArray[i][6];
				if(Execute.equals("Yes")){
					System.out.println("Starting Iteration : " + i);
					EmailAddress = xlLocalArray[i][0];
					Password = xlLocalArray[i][1];
					VerifyPassword = xlLocalArray[i][2];
					DisplayName = xlLocalArray[i][3];
					Country = xlLocalArray[i][4];
					Browser = xlLocalArray[i][5];
					
					SetBrowserType();
					test_SignUp();
					test_Login();
					//xlWrite(xlFilePath, xlLocalArray);
					System.out.println("End of Iteration " + i);				
				} else {
					System.out.println("Not Executing Row : " + i + " because its execute flag is set to No.");
				}

			} //end of for loop
		} catch(Exception e) {
			e.printStackTrace();
		}
	  
  }

  @Test
  public void CustomerNewAccountForWorldWide() {
	  System.out.println("Executing the code of CustomerNewAccountForWorldWide Test.....");
	  
  }
  
  public void SetBrowserType() {
		if(Browser.equals("Chrome")) {
			String BrowType = "webdriver.chrome.driver";
			String BrowPath = "C:/Selenium/chromedriver.exe";
			System.setProperty(BrowType, BrowPath);
			driver = new ChromeDriver();
		} else if(Browser.equals("FF")) {
			driver = new FirefoxDriver();
		} else {
			String BrowType = "webdriver.ie.driver";
			String BrowPath = "C:/Selenium/IEDriverServer.exe";
			System.setProperty(BrowType, BrowPath);
			driver = new InternetExplorerDriver();	
		} // end of if statement
		
	} //end of function : SetBrowserType
	
	public void test_SignUp() throws Exception {
		
//		System.setProperty(BrowType, BrowPath);
//		WebDriver driver = new ChromeDriver();
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("email")).sendKeys(EmailAddress);
		driver.findElement(By.id("password1")).sendKeys(Password);
		driver.findElement(By.id("password2")).sendKeys(VerifyPassword);
		driver.findElement(By.id("displayName")).sendKeys(DisplayName);
		new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(Country);
		driver.findElement(By.id("signUpButton")).click();
		
		String ExpConfMsg1 = "Thank you for creating a Screencast.com account!";
		String ConfMsg1 = driver.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
		if(ConfMsg1.equals(ExpConfMsg1)){
			System.out.println("SignUp functionality Passed. Confirmation Message that appeared is : " + ConfMsg1);
		} else {
			System.out.println("SignUp functionality Failed. Confirmation Message that appeared is : " + ConfMsg1);
		} //end of if statement
		
		driver.findElement(By.xpath("//*[@id='signedInAs']/a")).click();
		Thread.sleep(5000);
		

	} //end of function : test_SignUp


	public void test_Login() throws Exception {
//		System.setProperty(BrowType, BrowPath);
//		WebDriver driver = new ChromeDriver();
		
//		driver.get(url);
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("emailAddress")).sendKeys(EmailAddress);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.id("signInButton")).click();
		Thread.sleep(5000);
		
		String ExpConfMsg2 = "Signed in as " + DisplayName + "(Sign Out)";
		String ConfMsg2 = driver.findElement(By.xpath("//*[@id='signedInAs']")).getText();
		if(ConfMsg2.equals(ExpConfMsg2)) {
			System.out.println("Login functionality Passed. Confirmation Message that appeared is : " + ConfMsg2);
		} else {
			System.out.println("Login functionality Failed. Confirmation Message that appeared is : " + ConfMsg2);
		}
		
		driver.quit();
		
	} //end of function : test_Login
	
	public String[][] excelRead(String xlFilePath) throws Exception{
		System.out.println("xlFilePaht = " + xlFilePath);
		File fileHandle = new File(xlFilePath);
		FileInputStream testDataStream = new FileInputStream(fileHandle);
		HSSFWorkbook xlWorkbook = new HSSFWorkbook(testDataStream);
		HSSFSheet xlSheet = xlWorkbook.getSheetAt(0);
		xlNumOfRows = 	xlSheet.getLastRowNum()+1;
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
  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("Executing afterMethod function of CustomerNewAccount Test");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Executing afterClass function of CustomerNewAccount Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Executing afterTest function of CustomerNewAccount Test");
  }

}
