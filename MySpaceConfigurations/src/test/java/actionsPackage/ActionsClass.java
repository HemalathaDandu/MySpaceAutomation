package actionsPackage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pageObjects.PageObjectElements;

public class ActionsClass {

	public static void enterData(WebElement object, String data) {
		object.sendKeys(data);
	}

	public static void click(WebElement object) {
		object.click();
	}

	public static void clear(WebElement object) {
		object.clear();
	}

	public static Boolean displayed(WebElement object) {
		return object.isDisplayed();
	}

	public static String getText(WebElement object) {
		return object.getText();
	}

	public static void keyboardActions(WebElement object) {
		Actions act = new Actions(PageObjectElements.driver);
		act.sendKeys(Keys.ESCAPE).build().perform();
	}
}
