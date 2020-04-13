package tqs.airquality;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SeleniumTest.java
 *
 * @author Carina Neves
 */

@ExtendWith(SeleniumExtension.class)
public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void pullDown() throws Exception {
        driver.quit();
        String errorString = verificationErrors.toString();
        if (!"".equals(errorString)) {
            Assertions.fail(errorString);
        }
    }

    @Test
    public void test() throws Exception {
        driver.get("http://localhost:8080");
        List<String> cities = new ArrayList<>();
        String[] aux = {"Viana-do-Castelo", "Braga", "VilaReal", "Bragança", "Porto", "Aveiro", "Viseu", "Guarda",
                "Coimbra", "CasteloBranco", "Leiria", "Santarém", "Lisboa", "Portalegre", "Évora", "Setubal",
                "Beja", "Faro", "Funchal", "PontaDelgada"};

        for (String c : aux ) {
            cities.add(c);
        }

        for (String city : cities) {
            new Select(driver.findElement(By.id("selectCity"))).selectByVisibleText(city);
            driver.findElement(By.id("info")).click();
            driver.findElement(By.xpath("//button[@type='button']")).click();
        }
    }

    private boolean isElementPresent(By by) {
        driver.get("http://localhost:8080");
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void testPresence() {
        int count = 0;
        boolean presence;
        String[] aux = {"selectCity", "div0", "info"};

        List<String> elementes = new ArrayList<>();
        for (String s : aux) {
            elementes.add(s);
        }

        for (String elem : elementes) {
            presence = isElementPresent(By.id(elem));
            if (!presence)
                count++;
        }
        assertThat(count).isEqualTo(0);
    }

}
