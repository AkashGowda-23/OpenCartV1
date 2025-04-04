package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myaccnt;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement register;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement log;
	
	public void clickmyaccount() {
		myaccnt.click();
	}
	
	public void clickregister() {
		register.click();
	}
	public void login() {
		log.click();
	}
	
	

}
