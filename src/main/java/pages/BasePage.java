package pages;

import browser.Browser;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ExtentReport;

import java.util.List;
import java.util.function.Function;

public class BasePage {
    public void click(By locator) {
        Browser.webDriverWait60Sec().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public boolean AssertElementText(By locator, String text) {
        return Browser.webDriverWait60Sec().until(ExpectedConditions.presenceOfElementLocated(locator)).getText() == text;
    }

    public void EnterText(By locator, String text) {
        Browser.webDriverWait60Sec().until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public boolean IsEnabled(By locator){
        return Browser.webDriverWait60Sec().until(ExpectedConditions.visibilityOfElementLocated(locator)).isEnabled();
    }

    public boolean IsDisplayed(By locator){
        return Browser.webDriverWait60Sec().until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public void hover(By locator){
        Browser.actions().moveToElement(Browser.webDriverWait60Sec().until(ExpectedConditions.visibilityOfElementLocated(locator))).perform();
    }

    public WebElement findElement(Function<WebDriver, WebElement> expectedCondition) {
        return Browser.webDriverWait60Sec().until(expectedCondition);
    }

    public List<WebElement> findElements(Function<WebDriver, List<WebElement>> expectedCondition) {
        return Browser.webDriverWait60Sec().until(expectedCondition);
    }

    public void scrollUsingCoordinate(int coordinate) {
        Browser.javascriptExecutor().executeScript(String.format("window.scrollBy(0,%s)", coordinate));
    }

    public void scrollTillEndOfThePage(WebElement element) {
        Browser.javascriptExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollTillAnyElement(WebElement element) {
        Browser.javascriptExecutor().executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean isPageLoad(){
        return Browser.webDriverWait30Sec().until(
                webDriver -> Browser.javascriptExecutor().executeScript("return document.readyState").equals("complete"));
    }

    public boolean navigateTo(String url){
        Browser.driver().navigate().to(url);
        return isPageLoad();
    }
}
