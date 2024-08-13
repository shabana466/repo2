package datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		//stet1 get data the java representation object of the physical file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				     //step2 using properties file load from all the keys
				   Properties pObj=new Properties();
				   pObj.load(fis);
				  String BROWSER = pObj.getProperty("browser");
				String URL = pObj.getProperty("url");
				  String USERNAME = pObj.getProperty("username");
				  String PASSWORD = pObj.getProperty("password");
				  //Generate the random number
				  Random random=new Random();
					int randomInt = random.nextInt(1000);
					
				  
				//  Scanner s=new Scanner(System.in);
				 //  System.out.println("Browser");
				 //String browser = s.next();
				// System.out.println(browser);
				  //read data testcript fromexcel file
				 FileInputStream fis1=new FileInputStream("C:\\Users\\shaik shabana\\eclipse-workspace\\AdvanceAutomation\\src\\test\\resources\\testdata.xlsx");
					Workbook wb = WorkbookFactory.create(fis1);
					Sheet sh = wb.getSheet("Org");//sheet name
					Row row = sh.getRow(1);
				String orgName=row.getCell(2).toString()+randomInt;
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}//login the application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//navigate to organisation module 
		driver.findElement(By.linkText("Organizations")).click();
		//click on "create org"Button
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		//enter all details
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//step5 logout app
		Thread.sleep(2000);
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Thread.sleep(2000);
		logout.click();
		Actions act=new Actions(driver);
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		

		act.moveToElement(signout).perform();
		Thread.sleep(2000);
	
		driver.quit();

	}


}
