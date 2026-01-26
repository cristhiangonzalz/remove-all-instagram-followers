package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Unfollow {

    public Unfollow() {
    }

    private WebDriver driver = new ChromeDriver();

    public void login(String username, String pass) {
        driver.get("https://www.instagram.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username' or @name='email']")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password' or @name='pass']")));

        By byLoginButton = By.xpath("//*[(" + "self::button or self::div[@role='button']" + ") and contains(., 'Log in')]");


        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginButton));
        user.sendKeys(username);
        password.sendKeys(pass);
        loginButton.submit();

    }

    public void goToProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/' + username + '/?next=%2F' or @href='/'+ username +'/']")));
        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Profile']/ancestor::a")));
        profileButton.click();

    }

    public int getNumberOfFollowing() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a//span[text()=' following']/span")));
        return Integer.parseInt(element.getText());
    }

    public void openFollowing(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement following = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' following']/ancestor::a")));
        following.click();

    }

    public void unfollow() {
        int following = getNumberOfFollowing();
        int contador = 0;
        for (int i = 1; i <= following; i++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
            try {
                WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='dialog']//button[.//text()='Following' or text()='Requested']")));
                unfollowButton.click();
            } catch (TimeoutException e) {
                WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='dialog']//button[.//text()='Following' or text()='Requested']")));
                unfollowButton.click();
            }

            try {
                WebElement confirmUnfollow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Unfollow']")));
                confirmUnfollow.click();
                contador++;
            } catch (TimeoutException e) {
            }

            if (contador >= 12) {
                scroll(driver);
                contador = 0;
            }
        }
    }

    public void scroll(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='x6nl9eh x1a5l9x9 x7vuprf x1mg3h75 x1lliihq x1iyjqo2 xs83m0k xz65tgg x1rife3k x1n2onr6']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", dialog);
    }
}
