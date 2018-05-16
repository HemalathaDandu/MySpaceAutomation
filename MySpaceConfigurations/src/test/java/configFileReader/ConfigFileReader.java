package configFileReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	public static Properties prop;

	public static void configFileReader() {
		File propertyfilepath = new File("Configs//Config.properties");
		try {
			FileReader reader = new FileReader(propertyfilepath);
			prop = new Properties();
			prop.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getChromeDriverPath() {
		configFileReader();
		String driverpath = prop.getProperty("Chromedriverpath");
		return driverpath;
	}

	public static String getFirefoxDriverPath() {
		configFileReader();
		String driverpath = prop.getProperty("Firefoxdriverpath");
		return driverpath;
	}

	public static String getMyspaceUrl() {
		configFileReader();
		String url = prop.getProperty("MySpaceUrl");
		// System.out.println(url);
		return url;
	}

	public static String dataBaseConn() {
		configFileReader();
		String dataBaseConn = prop.getProperty("DataBaseConn");
		// System.out.println(url);
		return dataBaseConn;
	}

	public static String dbUsername() {
		configFileReader();
		String dbUsername = prop.getProperty("DBUsername");
		// System.out.println(url);
		return dbUsername;
	}

	public static String dbPassword() {
		configFileReader();
		String dbPassword = prop.getProperty("DBPwd");
		// System.out.println(url);
		return dbPassword;
	}
}
