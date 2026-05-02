package Testing;

import Pages.LoginPage;
import Pages.PIMPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PIMTest
{
    private WebDriver driver;
    private PIMPage pimPage;

    @BeforeMethod
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        var loginPage = new LoginPage(driver, 5);
        pimPage = new PIMPage(driver, 10, loginPage);
        pimPage.accessToPIM();
    }



    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
