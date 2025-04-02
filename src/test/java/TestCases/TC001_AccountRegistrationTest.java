package TestCases;
import static org.testng.Assert.assertEquals;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.AccountRgistrationPage;
import PageObject.HomePage;
import Testbase.BaseClass;

public class TC001_AccountRegistrationTest  extends BaseClass{	 
	 @Test(groups = {"Regression","MAster"})
	public void account_registeration() {
		 try {
		
		logger.info("---------------Starting TC001_AccountRegistrationTest ---------- ");
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		hp.clickregister();
		logger.info("------clicked on register button ------------------");
		
		AccountRgistrationPage ar= new AccountRgistrationPage(driver);
		ar.firstname("Jhon");
		ar.lastname("do");
		ar.email(random()+"@gmail.com");
		ar.phone("3456754325");
		
		//for genrating random password
		//String password=alphaNumeric(); //this is there in base class
		//ar.password(password);
		//ar.confirmpassword(password);
		ar.password("admin@123");
		ar.confirmpassword("admin@123");
		ar.checkpolicy();
		ar.register();
		String s=ar.validationmsg();
		Assert.assertEquals(s, "Your Account Has Been Created!");
		
		logger.info("----------register completed------------------");
	}
	 catch(Exception e) {
		 logger.error("Test Failed");
		 logger.debug("Debug Logs");
		 Assert.fail();
		 
	 }
	
	
	
	 }

}
