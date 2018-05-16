package MySpaceConfig.Maven;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import actionsPackage.ActionsClass;
import configFileReader.ConfigFileReader;
import excelReader.DataDrivenClass;
import pageObjects.PageObjectElements;

public class UtilizationClass extends PageObjectElements {
	public ScreenRecorder screenRecorder;

	@BeforeTest
	public void login() throws Exception {

		driver = new FirefoxDriver();
		startRecording();
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * ConfigFileReader.getChromeDriverPath());
		 * 
		 * PageObjectElements.driver = new ChromeDriver();
		 */
		driver.get(ConfigFileReader.getMyspaceUrl());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ActionsClass.enterData(userName(), DataDrivenClass.excelReader("Sheet1", 3, "UserName"));
		ActionsClass.enterData(passWord(), DataDrivenClass.excelReader("Sheet1", 3, "Password"));
		Thread.sleep(1000);
		ActionsClass.click(submitButton());
	}

	@Test(priority = 1)
	public static void myspaceLogin() throws IOException, Throwable {
		driver.manage().window().maximize();
		try {
			ActionsClass.keyboardActions(approvalNotificationCloseESC());
			if (ActionsClass.displayed(myspaceConfig()) == true) {
				System.out.println(ActionsClass.getText(myspaceConfig()));
				System.out.println("MySpace Configuration Module is Available:Test Case Pass");
			} else {
				System.out.println("Test Case Fail");
			}
		} catch (Exception e) {
			System.out.println("MySpace Configuration Module is not Available");
		}
	}

	@Test(priority = 2)
	public void mySpaceThemes() throws InterruptedException {
		Thread.sleep(2000);
		ActionsClass.click(myspaceConfig());
		ActionsClass.click(myspaceConfigThemes());
		System.out.println(ActionsClass.getText(myspaceConfigThemes()));
		Thread.sleep(5000);
		List<WebElement> ele = driver.findElements(By.xpath("//fieldset/div/ul/li"));
		List<WebElement> themeText = driver.findElements(By.xpath("//fieldset/div/ul/li/span"));
		for (WebElement element : themeText) {
			System.out.println(element.getText());
		}
		System.out.println("No.of Themes available in MySpace is: " + ele.size());
	}

	public void startRecording() throws Exception {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		this.screenRecorder = new ScreenRecorder(gc, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null);
		this.screenRecorder.start();
	}

	public void stopRecording() throws Exception {
		this.screenRecorder.stop();
	}

	@Test(priority = 3)
	public void mySpaceThemesDisplay() throws InterruptedException, IOException {
		List<WebElement> ele = driver.findElements(By.xpath("//fieldset/div/ul/li"));
		for (int i = 0; i <= ele.size() - 1; i++) {
			Thread.sleep(2000);
			// String themeName1 = ele.get(i).getAttribute("data-theme");
			ele.get(i).click();
			String themeName = ele.get(i).getAttribute("data-theme");
			// Assert.assertEquals(themeName, themeName1);
			System.out.println("MySpace Theme After Clicked: " + themeName);
		}
	}

	@Test(priority = 4)
	public void mySpaceActiveTheme() throws InterruptedException, IOException {
		Thread.sleep(2000);
		ActionsClass.click(myspaceConfig());
		ActionsClass.click(myspaceConfigThemes());
		System.out.println(ActionsClass.getText(myspaceConfigThemes()));
		System.out.println("MySpace Active Theme before: " + ActionsClass.getText(mySpaceActiveThemeName()));
	}

	@Test(priority = 5)
	public void mySpaceThemeSave() throws InterruptedException, IOException {
		WebElement themeText = driver
				.findElement(By.xpath("//section[@id='container']/div/div/div/div/fieldset[1]/div/ul/li[2]/span"));
		if (themeText.getText().equals("Summer")) {
			themeText.click();
			driver.findElement(By.xpath("//input[@class='submit_button']")).click();
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File("C:/Users/hsankrappa/workspace/MySpaceConfigurations/Screenshots/Screenshot.jpg"));
		}
		/*
		 * for (int i = 0; i < themeText.size() - 1; i++) { if
		 * (themeText.get(i).getText().equals("Emerald Green")) {
		 * themeText.get(i).click();
		 * driver.findElement(By.xpath("//input[@class='submit_button']")).click
		 * (); // System.out.println("clicked"); } }
		 */
	}

	// @Test(priority = 6)
	public void mySpaceActiveThemeAfterSave() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("MySpace Active Theme After Save is : " + ActionsClass.getText(mySpaceActiveThemeName()));
	}

	// @Test(priority = 5)
	public void rainyTheme() throws InterruptedException {
		Thread.sleep(2000);
		ActionsClass.click(myspaceConfig());
		ActionsClass.click(myspaceConfigThemes());
		WebElement element = driver.findElement(By.xpath("//fieldset[1]/div/ul/li[1]/img"));
		element.click();
		System.out.println(element.getAttribute("src"));
	}

	// @Test(priority = 6)
	public void summerTheme() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//fieldset[1]/div/ul/li[2]/img"));
		element.click();
		System.out.println(element.getAttribute("src"));
	}

	@AfterTest
	public void driverClose() throws Exception {
		stopRecording();
		driver.close();
	}
}
