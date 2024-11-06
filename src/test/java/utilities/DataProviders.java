package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class DataProviders {
	public Properties P;
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
	String path = ".\\testData\\Opencart_LoginData.xlsx";
	ExcelUtility xlutil = new ExcelUtility(path);
	
	int totalrows = xlutil.getRowCount("Sheet1");
	int totalcols = xlutil.getCellCount("Sheet1", 1);
	
	String logindata[][] = new String[totalrows][totalcols];
	for(int i=1;i<=totalrows;i++)
		{
		for(int j=0;j<totalcols;j++)
		{		
			logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
		}
		}
	return logindata;  //returning the two dimensional array
}
	
	@DataProvider(name="LoginDataFromRow")
	public String[][] getDataFromRow() throws IOException
	{
		FileInputStream propfile = new FileInputStream("./src//test//resources//config.properties");
		//FileReader file = new FileReader("Opencart_Hybrid_Framework\\src\\test\\resources\\config.properties");
		P = new Properties();
		P.load(propfile);
		
	String path = ".\\testData\\Opencart_LoginData.xlsx";
	ExcelUtility xlutil = new ExcelUtility(path);
	
	int totalrows = xlutil.getRowCount("Sheet1");
	int totalcols = xlutil.getCellCount("Sheet1", 1);
	String Row = P.getProperty("Row");
	int row = Integer.parseInt(Row);
	
	String logindata[][] = new String[1][totalcols];
	for(int i=1;i<=totalrows;i++)
	{
		if(i==row)
		{
		for(int j=0;j<totalcols;j++)
		{
			logindata[i-row][j] = xlutil.getCellData("Sheet1", i, j);
		}
		}
		
	}
	return logindata;  //returning the two dimensional array
}
	}
