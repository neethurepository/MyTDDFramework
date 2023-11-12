package pompackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseHRMClass;

public class POMLogin extends BaseHRMClass { //make BaseHRM class as the parent of POMLogin to use all the global variables and methods 
	//step1. create object repository
	//write all the elements on the Login page and their locators
	
	//@FindBy. is the replacement of driver.finElement(By.
	@FindBy(id="user-name") WebElement Username;
    @FindBy(id="password") WebElement Password;
    @FindBy(id="login-button") WebElement Loginbtn;
    
    //initiate page elements
    //create constructor of this pom calss
    
    public POMLogin() {
    	//initElement is a static method in Page Factory, using this we can initialize all the webelement specified bythi at@FindBy
    	//this keyword convert local variable into global variable
    	PageFactory.initElements(driver, this);	
    }
    //actions to be performed on the element is created as methods
    
    public void typeusername(String name) {
    	//driver.findElement(By.name("usename") is Usename
    	Username.sendKeys(name);
    }

    public void typePassword(String pass) {
    	//driver.findElement(By.name("usename") is Usename
    	Password.sendKeys(pass);
    }
    
    public void clickbtn() {
    	Loginbtn.click();
    }
    
    public String verify() {
    	return driver.getTitle();
    }
}
