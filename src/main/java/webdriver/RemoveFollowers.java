package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class RemoveFollowers {

    public RemoveFollowers(){}

    private final WebDriver driver = new ChromeDriver();

//        if (!username.isBlank()) {
//            WebDriver driver = new ChromeDriver();
//            driver.get("https://www.instagram.com/");
//            Unfollow.login(driver);
//            Unfollow.goToProfile(driver);
//            openFollowing(driver);
//            unfollow(driver, getNumberOfFollowing(driver));
//        }


    private int getNumberOfFollowing() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a//span[text()=' followers']/span")));
        assert element != null;
        return Integer.parseInt(element.getText());
    }

    public void openFollowing() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement following = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' followers']/ancestor::a")));
        assert following != null;
        following.click();

    }

    public void unfollow() {
        int following = getNumberOfFollowing();
        int contador = 0;
        for (int i = 1; i <= following; i++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            try {

                WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Remove']")));
                assert unfollowButton != null;
                unfollowButton.click();

            } catch (TimeoutException e) {
                WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Remove']")));
                assert unfollowButton != null;
                unfollowButton.click();
            }

            try {
                WebElement confirmUnfollow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Remove']")));
                assert confirmUnfollow != null;
                confirmUnfollow.click();
                contador++;
            } catch (TimeoutException ignored) {

            }

            if (contador >= 10) {
                scroll();
                contador = 0;
            }
        }
    }

    private void scroll() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement dialog = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x6nl9eh x1a5l9x9 x7vuprf x1mg3h75 x1lliihq x1iyjqo2 xs83m0k xz65tgg x1rife3k x1n2onr6']")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight",
                dialog
        );
    }
}

