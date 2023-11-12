package testLayer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseHRMClass;
import pompackage.POMLogin;
import testData.ExcelSheet;

public class LoginTest extends BaseHRMClass {
//all common methods are there in BaseHRMClass, so make BAseHRMClass as the parent of this class
	
	//create Constructor of LoginTest
	POMLogin Log;
	public LoginTest(){
		super();//to call the constructor of Parent class, reading the config.properties is there in the constructor of parent class
		
	}
	//super keyword reads all the properties of config file,accordingly the initiation method will work
	//because in the initiation method we are reading properties from config.properties
	
	@BeforeMethod //from TestNG
	public void initsetup() {
		initiation(); //initiation method of BaseHRMClass has browser,url information
		screenshots("Login");//calling screenshots method, screenshot should come as Login.jpg
	Log=new POMLogin();
	}
	
	@Test(priority=1)
	public void Title() {
     String actual=	Log.verify();//call methods from POMLogin class using the POMLogin Object reference variable
     System.out.println(actual);
		//Log.Verify gives the actual value, store it in a vraiabe
     Assert.assertEquals(actual,"Swag Labs");
		
	}
	
	@DataProvider
	public Object[][] Details(){
		Object result[][]=ExcelSheet.readdata("Sheet1");//Dataprovider will read data from Excelsheet class, readdata method i.e., from sheet1
		return result;
	}
	@Test(priority=2, dataProvider="Details")
	public void Login(String name,String password) throws InterruptedException {
		Log.typeusername(name);
		Thread.sleep(3000);
		Log.typeusername(password);
		//Log.clickbtn();
	}

	/*
	 * @Test(priority=2) public void Login() {
	 * Log.typeusername(prop.getProperty("username"));//takevalue from the
	 * config.properties file Log.typePassword(prop.getProperty("password"));
	 * Log.clickbtn(); }
	 */
	@AfterMethod
	public void close() {
		driver.close();
	}
}
