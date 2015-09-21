package nestor_sanchez.training.globant.com.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class HomePage {
	
	@FindBy(xpath="//*[@id=\"site-title\"]/span/a")
	private WebElement title;
	
	@FindBy(id="s")
	private WebElement searchForm;
	
	@FindBy(xpath="//*[@id=\"post-0\"]")
	private WebElement resultsSearch;
	
	@FindBy(id="today")
	private WebElement dayOfMonth;
	
	@FindBy(id="wp-calendar")
	private WebElement calendar;
	
	@FindBy(id="content")
	private WebElement layoutContent;
	
	public void go(WebDriver driver){
		driver.get("http://10.28.148.127/wordpress");
	}
	
	public boolean checkDateCreation(){
		boolean result=true;
		List<WebElement> dates=layoutContent.findElements(By.className("entry-date"));
		for(WebElement dateCreation:dates){
			//2013-10-10T14:36:10+00:00
			SimpleDateFormat formatterCreation = new SimpleDateFormat("yyyy-MM-dd");
			//October 10, 2013
			SimpleDateFormat formatterShowIt = new SimpleDateFormat("MMMM dd, yyyy",Locale.US);
			try {
				Date dateC = formatterCreation.parse(dateCreation.getAttribute("datetime").substring(0, 10));
				Date dateS=formatterShowIt.parse(dateCreation.getText());
				if(dateC.compareTo(dateS)!=0){
					result=false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Reporter.log(e.toString());
				result=false;
			}
		}
		return result;
	}
	
	public boolean search(String query){
		boolean result=false;
		searchForm.sendKeys(query);
		searchForm.submit();
		try{
			if(resultsSearch.getText().isEmpty()){
				result=true;
			}
		}catch(Exception e){
			result=true;
		}
		return result;
	}
	
	public String getTitle(){
		return title.getText();
	}
	
	public void calendarCheck(){
		WebElement table=calendar.findElement(By.tagName("tbody"));
		List<WebElement> allFormChildElements=table.findElements(By.tagName("a"));
		Reporter.log("This days "+(allFormChildElements.size())+" has posts");
		if(allFormChildElements.size()>0){
			WebElement item = allFormChildElements.get(0);
			Reporter.log(item.getText());
			item.click();
			
			List<WebElement> titles=layoutContent.findElements(By.className("entry-title"));
			Reporter.log("Numero de Posts "+titles.size());
			for(WebElement title:titles){
				Reporter.log("Titulo-"+title.getText());
			}
		}
	}
}
