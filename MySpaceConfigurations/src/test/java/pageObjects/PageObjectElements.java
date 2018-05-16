package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectElements {
	public static WebDriver driver;

	public static WebElement userName() {
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		return username;
	}

	public static WebElement passWord() {
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		return pwd;
	}

	public static WebElement submitButton() {
		WebElement submit = driver.findElement(By.xpath("//input[@id='submit']"));
		return submit;
	}

	public static WebElement myspaceConfig() {
		WebElement myspaceConfig = driver.findElement(By.xpath("//a[text()='Myspace Configuration']"));
		return myspaceConfig;
	}

	public static WebElement myspaceConfigThemes() {
		WebElement myspaceConfigThemes = driver.findElement(By.xpath("//a[text()='Themes']"));
		return myspaceConfigThemes;
	}

	public static WebElement approvalNotification() {
		WebElement approvalNotification = driver.findElement(By.xpath("//div[@id='TB_window']"));
		return approvalNotification;
	}

	public static WebElement approvalNotificationCloseESC() {
		WebElement approvalNotificationCloseESC = driver.findElement(By.xpath("//div[@id='TB_closeAjaxWindow']"));
		return approvalNotificationCloseESC;
	}

	public static WebElement mySpaceThemesCount() {
		List<WebElement> ele = driver.findElements(By.xpath("//fieldset/div/ul/li"));
		return (WebElement) ele;
	}

	public static WebElement mySpaceThemeNames() {
		List<WebElement> themeText = driver.findElements(By.xpath("//fieldset/div/ul/li/span"));
		return (WebElement) themeText;
	}

	public static WebElement mySpaceActiveThemeName() {
		WebElement activeTheme = driver.findElement(By.xpath("//li[@class='activeTheme']/span"));
		return activeTheme;
	}
}
