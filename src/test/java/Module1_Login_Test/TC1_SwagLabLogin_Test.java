package Module1_Login_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import module1_Login.SwagLabHomePage;
import module1_Login.SwagLabLoginPage;
import module1_Login.SwagLabMenuPage;

public class TC1_SwagLabLogin_Test extends BaseClass
{
	SwagLabLoginPage login;
	SwagLabHomePage home;
	SwagLabMenuPage menu;
	int TCID;
	
	@BeforeClass
	public void openBrowser() throws IOException 
	{
		initilizeBrowser();
		login=new SwagLabLoginPage(driver);
		home=new SwagLabHomePage(driver);
		menu=new SwagLabMenuPage(driver);
	}
	@BeforeMethod
	public void loginToApp() throws InterruptedException, IOException 
	{
		login.inpSwagLabLoginPageUserName(UtilityClass.getDataFromPF("UN"));
		Thread.sleep(1000);
		login.inpSwagLabLoginPagePass(UtilityClass.getDataFromPF("PWD"));
		Thread.sleep(1000);
		login.clickSwagLabLoginPageLoginBtn();
		Thread.sleep(1000);
	}
	@Test
	public void verifyTitle() throws EncryptedDocumentException, IOException 
	{
		TCID=105;
		String actTitle = home.getSwagLabHomePageTitle();
		String expTitle = UtilityClass.getTD(0, 0);
		
		Assert.assertEquals(actTitle, expTitle, "failed both results are failed");
	}
	@AfterMethod
	public void logoutFromApp(ITestResult s1) throws InterruptedException, IOException
	{
		if (s1.getStatus()==ITestResult.FAILURE)
		{
			UtilityClass.captureSS(driver,TCID);
		}
		home.clickSwagLabHomePageMenuBtn();
		Thread.sleep(1000);
		menu.clickSwagLabMenuPageLogoutBtn();	
		Thread.sleep(1000);
	}
	@AfterClass
	public void closeBrowser() 
	{
		driver.quit();
	}
}



