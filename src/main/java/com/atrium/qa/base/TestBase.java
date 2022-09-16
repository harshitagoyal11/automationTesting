package com.atrium.qa.base;
	import java.io.File;
	import java.io.FileInputStream;

	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.net.URISyntaxException;
	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.sl.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.StaleElementReferenceException;
	//import org.apache.log4j.Logger;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.events.EventFiringWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.atrium.qa.excel.utility.Xls_Reader;
	import com.atrium.qa.pages.LoginPage;
	//import selenium.webdriver.support.wait;
	import com.atrium.qa.util.TestUtil;
	//import com.atrium.qa.util.WebEventListener;

	// This file contains the commonly used methods which will be parent file for test cases file 
	
	public class TestBase   {

	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	//public static WebEventListener eventListener;
	    public static WebDriverWait webDriverWait;
	    LoginPage loginPage;
	    Xls_Reader reader = new Xls_Reader("/Atrium_Project/src/main/java/com/atrium/qa/testdata/AtriumTestData.xlsx");
	String sheetName = "RegTestData";
	public static List<Map<String,String>> testDataAllRows;


	//private Object ;

	public TestBase(){
	try {
	prop = new Properties();
	Properties config;
	// if( this.config == null )
	// {
//	    config = new Properties();
	// }

	final String propertyFilePath= "/Users/admin/eclipse-workspace/Atrium_Project/config.properties";
	FileInputStream ip = new FileInputStream(propertyFilePath);
	prop.load(ip);
	}  catch (IOException e) {
	e.printStackTrace();
	}
	}


	public static void initialization(){
	String browserName = prop.getProperty("browser");

	if(browserName.equals("chrome")){
	System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
	driver = new ChromeDriver();
	System.out.println("browser name is.................."+ browserName);
	}
	else if(browserName.equals("FF")){
	System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
	driver = new FirefoxDriver();
	}


	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	driver.get(prop.getProperty("url"));

	}


	public WebElement isLoadingIconDisplayed(WebElement we) {

	WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
	       .until(ExpectedConditions.elementToBeClickable(we));
	// Print the first result
	System.out.println(firstResult.getText());
	return firstResult;
	}

	public static List<Map<String,String>> getTestDataInMap() throws IOException{
	//  List<Map<String,String>> testDataAllRows=null;
	 
	 Map<String,String> testData=null;

	 try {
	  FileInputStream fileInputStream=new FileInputStream("/Users/admin/eclipse-workspace/Atrium_Project/src/main/java/com/atrium/qa/testdata/AtriumTestData.xlsx");
	  Workbook workbook=new XSSFWorkbook(fileInputStream);
	  org.apache.poi.ss.usermodel.Sheet sheet=workbook.getSheetAt(0);
	  int lastRowNumber=((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum();
	 
	  int lastColNumber=sheet.getRow(0).getLastCellNum();
	 
	  List list=new ArrayList();
	  for (int i = 0; i < lastColNumber; i++) {
	   Row row=sheet.getRow(0);
	   Cell cell=row.getCell(i);
	   String rowHeader=cell.getStringCellValue().trim();
	   list.add(rowHeader);
	 
	  testDataAllRows.add(testData);

	   }
	   
	  }
	 
	 catch (FileNotFoundException e) {
	  e.printStackTrace();
	 }
	return testDataAllRows;
	}

	}




