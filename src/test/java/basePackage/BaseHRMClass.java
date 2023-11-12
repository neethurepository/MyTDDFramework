package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtils;

public class BaseHRMClass {
	public static Properties prop=new Properties();//to read data from config.properties file,static to make this accessible in all methods
	public static WebDriver driver;//globally declare driver
	
	//Step1: create constructor of base class, to read the properties of config.properties file
	//pass the location of the config.properties file as parameter
	//use 2 try catch block, 1 for idf the file is not found
	//2 try catch ids for since we are loading the file there will be some IO Exception like if we cannot read data from file
	
	public BaseHRMClass(){
		try {
			//FileInputStream is aclass which is used to read data from any file
		FileInputStream file=new FileInputStream("C:\\Users\\neeth\\eclipse-workspace\\TDDFramework\\src\\test\\java\\environmentvariables\\Config.properties");
		prop.load(file);//to load the config.properties file
		} 
    
    catch(FileNotFoundException e) {
    	e.printStackTrace();//to print the exception
    }
          catch(IOException a) {
        	a.printStackTrace();  
          }
		}
 
	//Step2:create a method to keep all the common commands which is used in the child class 
		
		public static void initiation() { 
		//common commands-driver path,maximize,pageload,implicit,getting url
			
	//According to the browser value in config.properties file we need to run code here, don't want to be dependent on a specific browser
		//using reference variable of properties file read the browser value from config file
		String browsername=	prop.getProperty("browser"); //store the value in a String varaiable
		if(browsername.equals("Firefox")){
			System.setProperty("Webdriver.gecko.driver","geckodriver.exe");
			driver=new FirefoxDriver();
		}
			else if(browsername.equals("chrome")) {
			System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
			driver=new ChromeDriver();
			}
		driver.manage().window().maximize();
		//timeouts are also subjected to change. so create another class timeutils under Utility Package
		driver.manage().timeouts().pageLoadTimeout(TimeUtils.timepage,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url")); //reading value from property file
		
	
	}
         public static void screenshots(String Filename) {
         //convert webdriver object to take screenshot, so driver can take screenshot,getScreenShotmethod is used to create image file
        //and output should be file format
        File file=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Inorder to see the screenshot in our OS, use FileUtils to copy the screen shot from srcFile to the C Drive
        try {
       FileUtils.copyFile(file,new File("C:\\Users\\neeth\\eclipse-workspace\\TDDFramework\\src\\test\\java\\screenshots\\Screenshots"  
                                                                                                                     +Filename+".jpg"));
		//append it with Filename.jpg
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
	
		}
		


}


