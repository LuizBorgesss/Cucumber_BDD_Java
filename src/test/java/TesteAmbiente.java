import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAmbiente {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\luize\\Documents\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
    }
}
