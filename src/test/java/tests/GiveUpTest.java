package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GiveUpTest {
    private static final String TEST_URL = "https://playground.learnqa.ru/puzzle/triangle";
    private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(5);

    private WebDriver driver;

    @BeforeAll
    void initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
    }

    @AfterAll
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void verifyGiveUpButtonAndAnswersVisibility() {
        // Open test page
        driver.get(TEST_URL);

        // Find and verify surrender button
        WebElement surrenderButton = findElementByText("button", "Я сдаюсь");
        Assertions.assertNotNull(surrenderButton, "Surrender button should be present");

        // Click the button
        surrenderButton.click();

        // Verify answer elements visibility
        verifyElementDisplayed("Ссылка на ответы", "Answers link should be visible");
        verifyElementDisplayed("Спрятать ответы", "Hide answers button should be visible");
    }

    private WebElement findElementByText(String tag, String text) {
        return driver.findElement(By.xpath(String.format("//%s[text()='%s']", tag, text)));
    }

    private void verifyElementDisplayed(String elementText, String errorMessage) {
        WebElement element = findElementByText("*", elementText);
        Assertions.assertTrue(element.isDisplayed(), errorMessage);
    }
}