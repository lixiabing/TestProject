package listeners;

import base.TestBase;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;


public class AllureListeners implements ITestListener {

    @Attachment(value="Screenshot", type="image/png")
    public byte[] saveScreenshotOnFailure(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="stacktrace", type="text/plain")
    public static String saveLogs(String message){
        return message;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("stacktrace",result.getMethod().getConstructorOrMethod().getName());
        List<byte[]> screenshots = new ArrayList<>();
        screenshots.add(((TakesScreenshot)TestBase.getDriver()).getScreenshotAs(OutputType.BYTES));
        screenshots.stream().forEach((item)-> {
            Allure.addByteAttachmentAsync("Screenshot", "image/png", ()->item);
        });

    }
}
