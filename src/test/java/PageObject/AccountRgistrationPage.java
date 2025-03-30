package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRgistrationPage  extends BasePage{
	
	public AccountRgistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement fname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmpassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement ckpolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement register;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmmsg;
	
	public void firstname(String firstname) {
		fname.sendKeys(firstname);		
	}
	public void lastname(String lastname) {
		lname.sendKeys(lastname);
	}
	public void email(String email1) {
		email.sendKeys(email1);
		
	}
	public void phone(String phone) {
		telephone.sendKeys(phone);
	}
	public void password(String pswd) {
		password.sendKeys(pswd);
	}
	public void confirmpassword(String conpaswd) {
		confirmpassword.sendKeys(conpaswd);
	}
	public void checkpolicy() {
		ckpolicy.click();
	}
	public void register() {
		register.click();
	}
	public String validationmsg() {
		try {
			return (confirmmsg.getText());
			
		}
		catch(Exception e) {
			return(e.getMessage());
		}
		
	}

}
