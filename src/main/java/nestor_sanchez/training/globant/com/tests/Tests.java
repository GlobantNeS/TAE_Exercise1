package nestor_sanchez.training.globant.com.tests;


import junit.framework.Assert;
import nestor_sanchez.training.globant.com.pages.ContactPage;
import nestor_sanchez.training.globant.com.pages.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Tests {

	WebDriver driver;
	
	@BeforeMethod
	public void before(){
		driver = new FirefoxDriver();
	}
	
	@AfterMethod
	public void after(){
		driver.quit();
	}
	
	@Test(timeOut=10000)
	public void checkTitle(){
		
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		Reporter.log("Entrando a Automation Training");
		Assert.assertEquals("Automation Training", homePage.getTitle());
		Reporter.log("El titulo concuerda");
		
	}
	
	@Test(timeOut=10000)
	public void checkSearch(){
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		Assert.assertEquals(true, homePage.search("meets"));
		Reporter.log("Busqueda realizada");
		
	}
	
	@Test	
	public void dateCreationTest(){
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		homePage.checkDateCreation();
		Reporter.log("Calendario Checado");
	}
	
	@Test
	public void fillContactComplete(){
		ContactPage contactPage=PageFactory.initElements(driver, ContactPage.class);
		contactPage.go(driver);
		Assert.assertEquals(true, contactPage.fillContact("Nestor", "nestor.sanchez@globant.com", "Prueba Test", "Un buen mensaje para Test"));
		Reporter.log("Contacto Completo");
	}
	
	@Test	
	public void fillContactNoComplete(){
		ContactPage contactPage=PageFactory.initElements(driver, ContactPage.class);
		contactPage.go(driver);
		Assert.assertEquals(true, contactPage.fillContactNoOk("Nestor", "nestor.sanchez@globant.com", "Prueba Test", "Un buen mensaje para Test"));
		Reporter.log("Contacto incompleto");
		Assert.assertEquals(true, contactPage.fillContact("Nestor", "nestor.sanchez@globant.com", "Prueba Test", "Un buen mensaje para Test"));
		Reporter.log("Contacto Completo");
	}
	
	@Test	
	public void calendarTest(){
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		homePage.calendarCheck();
		Reporter.log("Calendario Checado");
	}

}
