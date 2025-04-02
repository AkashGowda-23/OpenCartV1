package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import Testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass {
	
	
	/*
	 * data is valid = login is sucess -logout
	 * data is valid = login fail
	 * 
	 * data is invalid -login is success - test failed 
	 * data is invalid --login failed- test passed
	 * 
	 */
	
	@Test(dataProvider = "LoginData", dataProviderClass = utilities.DataProviders.class , groups = "Datadriven")
   public  void verify_TC003_LoginDDTest(String email1, String paswd , String exp) {
	       //homepage
	 		HomePage hp=new HomePage(driver);
	 		hp.clickmyaccount();
	 		hp.login();
	 		
	 		//login 
	 		LoginPage log= new LoginPage(driver);
	 		log.email(email1);
	 		log.password(paswd);
	 		log.submit();
	 		
	 		
	 		//my account page for validation 
			 MyAccountPage mya= new  MyAccountPage(driver);
			 boolean exp_res=mya.isMyAccountexist();
			 
			 if(exp.equalsIgnoreCase("Valid")) {
				 if(exp_res==true) {
					 mya.logout(); 
					 Assert.assertTrue(true);
				
					 
				 }
				 else
				 {
					 Assert.assertTrue(false);
					 
				 }
			 }
			 
			 if(exp.equalsIgnoreCase("invalid")) {
				 if(exp_res==true) {
					 mya.logout(); 
					 Assert.assertTrue(false);
				 }
				 else {
					 Assert.assertTrue(true);
				 }
				 
			 }
	 		
   }

}
