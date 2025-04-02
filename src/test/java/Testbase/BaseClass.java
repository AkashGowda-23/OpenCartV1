package Testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;//log4j	
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	 public static WebDriver  driver;
	 public Logger logger; //Log4j
	 public Properties p;
	 
	 
	 @BeforeClass(groups = {"Regression","Sanity","Master"})
	 @Parameters({"os","browser"})
		public void setuo(String os,String br) throws IOException {
		 
		 
		 //Loading configuration.properties file
		 FileReader file = new FileReader("./src/test/resources/config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 

		     logger=LogManager.getLogger(this.getClass());
		     
		     if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
		    	 String huburl="";
		    	 DesiredCapabilities capablities = new  DesiredCapabilities();
		    	 if(os.equalsIgnoreCase("windows")) {
		    		 capablities.setPlatform(Platform.WIN11);
		    	 }
		    	 else if(os.equalsIgnoreCase("mac")) {
		    		 capablities.setPlatform(Platform.WIN11);
		    	 }
		    	 else {
		    		 System.out.println("Invalid browser");
		    		 return ;
		    		 
		    	 }
		    	 switch(br.toLowerCase()) {
		    	 case "chrome" : capablities.setBrowserName("chrome"); break;
		    	 case "edge"   : capablities.setBrowserName("edge"); break;
		    	 default : System.out.println("invalid broswer"); return ;
		    	 
		    	 }
		    	 driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capablities);
		     }
		     
		     if(p.getProperty("execution_env").equalsIgnoreCase("local")){
		     switch(br.toLowerCase()) {
		     case "chrome" : driver=new ChromeDriver(); break;
		     case "edge" : driver= new EdgeDriver(); break;
		     default  :System.out.println("Invalid browser"); return;
		     }}
			 
			 driver.manage().deleteAllCookies(); //delete all the cookies
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get(p.getProperty("appURL")); //reading URL from the properties file
			 driver.manage().window().maximize();
		}
	 @AfterClass(groups = {"Regression","Sanity","Master"})
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
		

		   /* public String captureScreen(String testName) throws IOException {
		        if (driver instanceof TakesScreenshot) {
		            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		            String destPath = "./screenshots/" + testName + ".png";
		            FileUtils.copyFile(screenshot, new File(destPath));
		            return destPath;
		        } else {
		            throw new IllegalStateException("Driver does not support screenshots");
		        }
		    }*/
		

		public String captureScreen(String tname) throws IOException {
			String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		     
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetfilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_"+timestamp+".png";
			File tagetFile= new File(targetfilePath);
			
			sourceFile.renameTo(tagetFile);
			return targetfilePath;
			
			
		
		}
}
