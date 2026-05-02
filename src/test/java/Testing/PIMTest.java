package Testing;

import Pages.LoginPage;
import Pages.PIMPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    // TC: P-PIM-02Y
    @Test
    public void removeEmployeeTest()
    {
        String id = "ingresar id de empleado a eliminar";
        pimPage.setEmployeeIdValue(id);
        pimPage.clickSearchButton();
        pimPage.clickSearchResultButton();
        pimPage.clickDeleteButton();
        pimPage.clickDeleteConfirmationButton();
        pimPage.waitForEmployeeListContainer();
        // reseteamos los campos del formulario ya que al no haber cambiado de pagina se mantuvo el mismo valor a
        // pesar de eliminarlo
        pimPage.clickResetButton();
        pimPage.setEmployeeIdValue(id);
        pimPage.clickSearchButton();
        Assert.assertEquals(pimPage.getNoRecordsText(), "No Records Found");
    }
    // TC: P-PIM-03Y
    @Test
    public void editEmployeeTest()
    {
        String id = "ingresar id de empleado a editar (que no tenga nacionalidad seleccionada)";
        pimPage.setEmployeeIdValue(id);
        pimPage.clickSearchButton();
        pimPage.clickEditButton();
        pimPage.setNationalityDropDownValue();
        pimPage.clickSaveButton();
        pimPage.waitForPersonalDetailsRefresh();
        pimPage.clickEmployeeListButton();
        pimPage.setEmployeeIdValue(id);
        pimPage.clickSearchButton();
        pimPage.clickEditButton();
        Assert.assertEquals(pimPage.getNationalityDropDownValue(), "American");
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
