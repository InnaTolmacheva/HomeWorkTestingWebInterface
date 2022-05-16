package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class СardApplicationTest {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void close() {
        driver.quit();      //закрывает все окна браузера
        driver = null;      // обнуляем драйвер
    }

    @Test
    public void testWithCssSelector() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Вера-Мира У");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+71111111111");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();

        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);


    }

}
