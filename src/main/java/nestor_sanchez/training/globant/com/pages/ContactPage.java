package nestor_sanchez.training.globant.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class ContactPage {
	
	@FindBy(id="cntctfrm_contact_name")
	private WebElement nameContact;
	
	@FindBy(id="cntctfrm_contact_email")
	private WebElement mailContact;
	
	@FindBy(id="cntctfrm_contact_subject")
	private WebElement subjectContact;
	
	@FindBy(id="cntctfrm_contact_message")
	private WebElement messageContact;
	
	
	@FindBy(id="cntctfrm_contact_form")
	private WebElement contactForm;

	
	@FindBy(id="cntctfrm_thanks")
	private WebElement messageThanks; 
	
	
	public void go(WebDriver driver){
		driver.get("http://10.28.148.127/wordpress/sample-page/");
	}
	
	public boolean fillContact(String name,String mail,String subject,String message){
		boolean result=false;
		nameContact.clear();
		mailContact.clear();
		subjectContact.clear();
		messageContact.clear();
		nameContact.sendKeys(name);
		mailContact.sendKeys(mail);
		subjectContact.sendKeys(subject);
		messageContact.sendKeys(message);
		contactForm.submit();
		try{
			Reporter.log(messageThanks.getText());
			if(!messageThanks.getText().isEmpty()){
				result=true;
			}
		}catch(Exception e){
			result=false;
		}
		return result;
	}
	
	public boolean fillContactNoOk(String name,String mail,String subject,String message){
		boolean result=false;
		nameContact.clear();
		mailContact.clear();
		subjectContact.clear();
		messageContact.clear();
		//nameContact.sendKeys(name);
		mailContact.sendKeys(mail);
		subjectContact.sendKeys(subject);
		messageContact.sendKeys(message);
		contactForm.submit();
		try{
			Reporter.log(messageThanks.getText());
			if(!messageThanks.getText().isEmpty()){
				result=false;
			}
		}catch(Exception e){
			result=true;
		}
		return result;
	}
	

}
