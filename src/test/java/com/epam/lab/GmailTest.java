package com.epam.lab;


import com.epam.lab.consts.Constants;
import com.epam.lab.models.Message;
import com.epam.lab.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class GmailTest {

    Actions keyAction = null;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        keyAction = new Actions(driver);
    }

    @Test(priority = 0)
    public void openMailPageTest() {
        driver.get("https://www.google.com/gmail/");
    }

    @Test(priority = 1)
    public void loginTest() {
        User user = Constants.DEFAULT_USER;
        String username = user.getLogin();
        String password = user.getPassword();
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys(username);
        WebElement butt = driver.findElement(By.id("identifierNext"));
        butt.click();
        (new WebDriverWait(driver, 10)).until((dr) -> dr.findElement(By.id("profileIdentifier")).getText().contains(username.toLowerCase()));
        input = driver.findElement(By.name("password"));
        input.sendKeys(password);
        (new WebDriverWait(driver,10)).until((dr)->dr.findElement(By.id("passwordNext"))).click();
        (new WebDriverWait(driver, 10)).until((dr) -> dr.findElement(By.xpath("//*[@id=\"gb\"]/div[1]/div[1]/div[2]/div[4]/div[1]/a/span/..")).getAttribute("title").contains(username));
    }

    @Test(priority = 2)
    public void writeMessageTest() {
        Message message = Constants.WHATS_UP_MESSAGE;
        driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
        driver.findElement(By.name("to")).sendKeys(message.getTo());
        keyAction.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("c").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).perform();
        driver.findElement(By.name("cc")).sendKeys(message.getCc());
        keyAction.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("b").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).perform();
        driver.findElement(By.name("bcc")).sendKeys(message.getBcc());
        driver.findElement(By.name("subjectbox")).sendKeys(message.getSubject());
        driver.findElement(By.cssSelector("div[aria-label='Message Body']")).sendKeys(message.getMessage());
        driver.findElement(By.cssSelector("img[data-tooltip='Save & Close']")).click();
    }


    private String formatEmailToUnknownUsersMail(String email) {
        if (email.contains("<")) {
            email = email.substring(email.indexOf("<") + 1, email.indexOf(">"));
        }
        return email;
    }

    @Test(priority = 3)
    public void getPrevioslySavedMessageTest() {
        String subject = Constants.WHATS_UP_MESSAGE.getSubject();
        driver.findElement(By.partialLinkText("Drafts")).click();
        (new WebDriverWait(driver, 10)).until((dr) -> dr.getTitle().toLowerCase().contains("drafts"));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("tr[class ='zA yO']:first-child"))).build().perform();
        driver.findElement(By.cssSelector("tr[class ='zA yO aqw']:first-child")).click();

        Message resultMsg = new Message();
        String to = driver.findElement(By.name("to")).getAttribute("value");
        String cc = driver.findElement(By.name("cc")).getAttribute("value");
        String bcc = driver.findElement(By.name("bcc")).getAttribute("value");

        resultMsg.setTo(formatEmailToUnknownUsersMail(to));
        resultMsg.setCc(formatEmailToUnknownUsersMail(cc));
        resultMsg.setBcc(formatEmailToUnknownUsersMail(bcc));
        resultMsg.setSubject(driver.findElement(By.cssSelector("input[name='subject'")).getAttribute("value"));
        String messageBody = driver.findElement(By.cssSelector("div[aria-label='Message Body']")).getText();
        resultMsg.setMessage(messageBody);

        assertEquals(Constants.WHATS_UP_MESSAGE, resultMsg);

        driver.findElement(By.cssSelector("div[aria-label=\"Send \u202A(Ctrl-Enter)\u202C\"]")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
