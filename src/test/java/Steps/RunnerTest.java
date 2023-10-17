package Steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/cadastro_contas.feature",
        tags = "not @ignore",
        plugin = {"pretty"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = true
        //"html:target/report-html"
)
public class RunnerTest {
    @BeforeClass
    public static void reset(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("a@a");
        driver.findElement(By.name("senha")).sendKeys("a");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
    }
}






