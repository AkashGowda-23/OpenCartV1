package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".\\testdata\\Opencart_LoginData.xlsx";  //takimg xl file from testdata
		ExcelUtility xlutil = new ExcelUtility(path);        //creating an object for XLUtility 
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcol=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcol]; 
		
		for (int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcol;j++) {
				logindata[i-1][j]=xlutil.getCelldata("Sheet1", i, j);
				
			}
			
		}
		return logindata;
		
	}

}
