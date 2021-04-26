package pages;

import browser.Browser;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ExtentReport;

import java.util.List;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {
    private static final String url = "https://developers.bringg.com/";
    private static final String BASE_TEXT_FINDER = "//*[normalize-space(text())='{textSearch}']";
    private static final By FEATURE_SUMMARY_TABLE_ELEMENT = By.cssSelector("table.param_table_col_1_wider");
    private static final By FEATURE_SUMMARY_TABLE = By.id("section-feature-summary");
    private static final By TASK_TABLE = By.xpath("(//table)[5]");


    public String Url() {
        return url;
    }

    public void openMenu(String text) {
        this.click(By.xpath(BASE_TEXT_FINDER.replace("{textSearch}", text)));
    }

    public boolean verifyFeatureSummaryTable(List<String> values) throws InterruptedException {
        boolean res = isPageLoad();

        // Scroll down until start of table become visible.
        this.scrollTillAnyElement(featureSummaryTable());

        // Get table element.
        WebElement table = Browser.webDriverWait60Sec().until(ExpectedConditions.presenceOfElementLocated(FEATURE_SUMMARY_TABLE_ELEMENT));

        return verifyRows(table, values);
    }

    public boolean verifyTaskTable(List<String> values) {
        boolean res = isPageLoad();

        // Scroll down until start of table become visible.
        this.scrollTillAnyElement(featureSummaryTable());

        // Get table element.
        WebElement table = Browser.webDriverWait60Sec().until(ExpectedConditions.presenceOfElementLocated(FEATURE_SUMMARY_TABLE_ELEMENT));
        String href = getHrefByText(table, "Task");
        navigateTo(href);

        WebElement taskTable = Browser.webDriverWait60Sec().until(ExpectedConditions.visibilityOfElementLocated(TASK_TABLE));
        return verifyRows(taskTable, values);
    }

    private boolean verifyRows(WebElement table, List<String> values) {
        // For each row, check against expected rows list.
        for (WebElement p : table.findElements(By.tagName("p"))) {
            ExtentReport.extentTest.log(Status.INFO, String.format("Current row value: '%s'", p.getText()));
            if (values.contains(p.getText())) {
                ExtentReport.extentTest.log(Status.INFO, "Row exist inside expected rows list, remove item");
                values.remove(p.getText());
            }
        }

        // Return list should be empty.
        return values.size() == 0;
    }

    private String getHrefByText(WebElement table, String linkText) {
        for (WebElement p : table.findElements(By.tagName("p"))) {
            String href = getHrefLink(p, linkText);
            if (href != null)
                return href;
        }

        return null;
    }

    private String getHrefLink(WebElement element, String linkText) {
        try {
            // Find a element under p element and parse href string.
            WebElement a = element.findElement(By.tagName("a"));

            // In case link exist, verify text.
            if (a.findElement(By.tagName("span")).getText().equals(linkText))
                return a.getAttribute("href");
        } catch (NoSuchElementException ex) {
            // No a element found under given p element.
        }

        return null;
    }

    private WebElement featureSummaryTable() {
        return Browser.webDriverWait60Sec().until(ExpectedConditions.presenceOfElementLocated(FEATURE_SUMMARY_TABLE));
    }

    private WebElement taskTable() {
        return Browser.webDriverWait60Sec().until(ExpectedConditions.presenceOfElementLocated(TASK_TABLE));
    }

    public void goTo() {
        navigateTo(url);
        ExtentReport.extentTest.log(Status.INFO, String.format("Navigate to '%s'", url));
    }
}
