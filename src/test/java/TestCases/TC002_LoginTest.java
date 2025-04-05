package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import Testbase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info(" *****************************************************Starting TC002_Login Test *************************************************");
		
		try {
		
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		hp.login();
		
		//login 
		LoginPage log= new LoginPage(driver);
		log.email(p.getProperty("email"));
		log.password(p.getProperty("password"));
		log.submit();
		
		//my account page for validation 
		MyAccountPage mya= new  MyAccountPage(driver);
		 boolean exp_res=mya.isMyAccountexist();
		 Assert.assertEquals(exp_res, true,"login failed");	
		
	}
	catch(Exception e ) {
		logger.error("TEst failed");
		logger.debug("debug messages");
		Assert.fail();
		
	}
	
	

}}
