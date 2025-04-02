package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;
	
	public void email(String emailadress) {
		email.sendKeys(emailadress);
	}
	public void password(String pswd) {
		password.sendKeys(pswd);
	}
	public void submit() {
		login.click();
	}
	
	

}
