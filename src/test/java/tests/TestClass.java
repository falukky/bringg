package tests;
import browser.Browser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.Pages;
import utils.ExtentReport;
import java.util.ArrayList;
import java.util.List;

public class TestClass {

    @Before
    public void setup() throws Exception {
        ExtentReport.init();
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Setup");
        Browser.open();
    }

    @Test
    public void verifyFeatureSummaryTableRows() throws Exception {
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Verify 'Feature Summary' table",
                "This test verify table rows");
        Pages.HomePage().goTo();
        Pages.HomePage().openMenu("Driver SDK for iOS");
        Pages.HomePage().openMenu("tasksManager");
        Assert.assertTrue(Pages.HomePage().verifyFeatureSummaryTable(getFeatureSummaryTableExpectedRows()));
    }

    @Test
    public void verifyTaskTableRows() throws Exception {
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Verify 'Task' table",
                "This test verify table rows");
        Pages.HomePage().goTo();
        Pages.HomePage().openMenu("Driver SDK for iOS");
        Pages.HomePage().openMenu("tasksManager");
        Assert.assertTrue(Pages.HomePage().verifyTaskTable(getTaskTableExpectedRows()));
    }

    @After
    public void cleanup() throws Exception {
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Cleanup", "Clean system");
        Browser.close();
        ExtentReport.extentReports.flush();
    }

    private List<String> getFeatureSummaryTableExpectedRows(){
        List<String> rows = new ArrayList<String>();
        rows.add("tasksManager Property");
        rows.add("Functionality");
        rows.add("currentTask");
        rows.add("Optional");
        rows.add("If the user is currently working on a task, this property returns a Bringg Task data type. If the current user is not working on a task, the property returns nil.\n\nNote: Bringg data types are immutable.");
        return rows;
    }

    private List<String> getTaskTableExpectedRows(){
        List<String> rows = new ArrayList<String>();
        rows.add("Id");
        rows.add("Int");
        rows.add("The Id of the task.");
        rows.add("activeWaypointId");
        rows.add("Int");
        rows.add("The Id of the way point which the driver is currently servicing.");
        rows.add("asap");
        rows.add("boolean");
        rows.add("Indicates whether this is an ASAP task. The values are:");
        rows.add("The default value is false.");
        rows.add("externalId");
        rows.add("Int");
        rows.add("The Id of this task in your own system.");
        rows.add("priority");
        rows.add("String");
        rows.add("The priority of this task.");
        rows.add("status");
        rows.add("Int");
        rows.add("The status of this task. The values are:");
        rows.add("title");
        rows.add("String");
        rows.add("The title of this task.");
        rows.add("waypoints");
        rows.add("Object");
        rows.add("A way points object containing information about the way points in this task.");
        return rows;
    }
}
