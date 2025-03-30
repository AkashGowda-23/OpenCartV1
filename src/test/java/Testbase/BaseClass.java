package Testbase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;//log4j	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	 public WebDriver  driver;
	 public Logger logger; //Log4j
	 public Properties p;
	 
	 
	 @BeforeClass
	 @Parameters({"os","browser"})
		public void setuo(String os,String br) throws IOException {
		 
		 
		 //Loading configuration.properties file
		 FileReader file = new FileReader("./src/test/resources/config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 
		 
		 
		     logger=LogManager.getLogger(this.getClass());
		     
		     switch(br.toLowerCase()) {
		     case "chrome" : driver=new ChromeDriver(); break;
		     case "edge" : driver= new EdgeDriver(); break;
		     default  :System.out.println("Invalid browser"); return;
		     }
			 
			 driver.manage().deleteAllCookies(); //delete all the cookies
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
			 driver.get(p.getProperty("appURL")); //reading URL from the properties file
			 driver.manage().window().maximize();
		}
	 @AfterClass
		public void tearDown() {
			driver.quit();
			
		}
		
		public String random() {
			String genaratedstring=RandomStringUtils.randomAlphabetic(5);
			return genaratedstring;
		}
		public  String randomNumebrs() {
			String genratednumber=RandomStringUtils.randomNumeric(10);
			return genratednumber;
		}
		
		public  String alphaNumeric() {
			String genaratedstring=RandomStringUtils.randomAlphabetic(3);
			String genratednumber=RandomStringUtils.randomNumeric(3);
			return (genaratedstring+genratednumber);
		}
}
