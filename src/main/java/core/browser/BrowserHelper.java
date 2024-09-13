package core.browser;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class BrowserHelper {

    public static void open(String url, Locale locale) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--no-zygote");
        options.addArguments(String.format("--lang=%1$s",locale.toString()));
        Selenide.open(url, new SelenideConfig().browserCapabilities(options));
    }

}
