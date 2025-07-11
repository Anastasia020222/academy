package appium.extension;

import appium.manager.Driver;
import appium.manager.AndroidManager;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;

public class AndroidExtension implements BeforeEachCallback, AfterEachCallback {

    private AndroidDriver androidDriver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        androidDriver = AndroidManager.getAndroidDriver();

        Object testInstance = extensionContext.getRequiredTestInstance();
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Driver.class) && field.getType().equals(AndroidDriver.class)) {
                field.setAccessible(true);
                field.set(testInstance, androidDriver);
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
