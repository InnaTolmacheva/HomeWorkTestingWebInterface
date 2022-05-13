package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class cardApplicationTest {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    //    driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.quit();      //закрывает все окна браузера
        driver = null;      // обнуляем драйвер
    }

    @Test
    public void testWithList() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Теркин Василий");
        elements.get(1).sendKeys("+71111111111");

        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();

        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }

    @Test
    public void testWithCssSelector() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Теркин Василий");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+71111111111");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();

        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);


    }

}
