package appium.manager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class AndroidManager {

    public static AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "16");

        String apkPath = Paths.get("src/test/resources/ApiDemos-debug.apk").toAbsolutePath().toString();
        capabilities.setCapability("appium:app", apkPath);

        capabilities.setCapability("appium:automationName", "UiAutomator2");

        try {
            return new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
