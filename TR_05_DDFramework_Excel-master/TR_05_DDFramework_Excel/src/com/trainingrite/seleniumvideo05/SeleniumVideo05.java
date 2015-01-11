package com.trainingrite.seleniumvideo05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.xerces.impl.dv.xs.FullDVFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.webdriven.commands.SelectWindow;
import com.thoughtworks.selenium.webdriven.commands.WindowFocus;

public class SeleniumVideo05 {
	public WebDriver driver;
	String WebDriverPath;
	String WebDriverType;
	
	
	String url = "http://www.screencast.com";
	//String xlFilePath = "/Users/FerozMac/Documents/Tools/Selenium/workspace/TR_SeleniumVideo05/TestDataScreenCast.xls";
	String xlFilePath = "C:/Users/DELL/Documents/Selenium Projects-Workspace/TR_05_DDFramework_Excel-master/TR_05_DDFramework_Excel/TestDataScreenCast.xls";
	String userName;
	String passWord;
	String verifyPassWord;
	String displayName;
	String country;
	String dropdownList;
	String confirmMessage = "Thank you for creating a Screencast.com account!";
	int xlNumOfRows, xlNumOfCols;
	String xlLocalArray[][];
	String browserType;
	
	
	@Before
	public void setUp() throws Exception {
		excelRead(xlFilePath);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		
	}
	
	public void setBrowser(String browserType) throws Exception{
		if (browserType.equals("Chrome")){
			WebDriverPath = "/Users/FerozMac/Documents/Tools/Selenium/Jars/chromedriver";
			WebDriverType =  "webdriver.chrome.driver";
			System.setProperty(WebDriverType,WebDriverPath);
			driver = new ChromeDriver();
			
		}else if(browserType.equals("FF")){
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

	@Test
	public void driverScript() throws Exception {
		try{
				for (int i=1; i<xlNumOfRows ; i++){ //i=0 is first row in excel
					System.out.println("Executing Row: "+ i);
					userName = xlLocalArray[i][0];
					passWord = xlLocalArray[i][1];
					verifyPassWord = xlLocalArray[i][2];
					displayName = xlLocalArray[i][3];
					country = xlLocalArray[i][4];
					browserType = xlLocalArray[i][5];
					testNewAccount();
					testLogin();
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testNewAccount() throws Exception {
		//System.setProperty(WebDriverType,WebDriverPath);
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--start-maximized");
		//driver = new ChromeDriver(options);
		//((JavascriptExecutor) driver).executeScript("window.focus();");
		//String winHandle = driver.getWindowHandle();
		//driver.switchTo().window(winHandle);
		setBrowser(browserType);
		driver.get(url);
		driver.findElement(By.id("email")).sendKeys(userName);
		driver.findElement(By.id("password1")).sendKeys(passWord);
		driver.findElement(By.id("password2")).sendKeys(verifyPassWord);
		driver.findElement(By.id("displayName")).sendKeys(displayName);
		Select dropdownList = new Select(driver.findElement(By.id("countrySelect")));
		dropdownList.selectByVisibleText(country);
		//new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(country);
		driver.findElement(By.id("signUpButton")).click();
		Thread.sleep(2000);
		try{
			String outputMessage = driver.findElement(By.xpath("//*[@id='container']/div[3]/h3")).getText();
			if (confirmMessage.equals(outputMessage))
				System.out.println("Test Passed");
			else
				System.out.println("Test Failed");	
		}catch(Exception e){
			System.out.println("Test Failed - Account not created");
		}
		
		tearDown();
		
	}
	
	
	public void testLogin() throws Exception{
		//System.setProperty(WebDriverType,WebDriverPath);
		//driver = new ChromeDriver();		
		//driver.manage().window().maximize();
		setBrowser(browserType);
		driver.get(url);
		driver.findElement(By.id("emailAddress")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(passWord);
		driver.findElement(By.id("signInButton")).click();
		Thread.sleep(2000); //wait till the page loads
		try {
			driver.findElement(By.id("signedInAs"));
			System.out.println("Test Passed");
		}catch (NoSuchElementException e){
			System.out.println("Test Failed");
		}
		
		tearDown();
		
	}
	
	public String[][] excelRead(String xlFilePath) throws Exception{
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
	}
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
	}

}
