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

    // TC: P-PIM-01P
    @Test
    public void employeeListTest_P_PIM_01P()
    {
        // Datos de prueba
        String employeeName = "Arun K";

        pimPage.setEmployeeNameValue(employeeName);
        pimPage.selectFirstEmployeeNameOption();
        pimPage.clickSearchButton();
        pimPage.waitForEmployeeListContainer();
        // El resultado esperado (segun tu caso de prueba manual) indica que si el usuario buscó por empleado, le deberían salir todos los registros con su nombre.
        // El resultado obtenido fue que SOLO devolvió 1, por lo  que el estado de la prueba será FAIL.
        int results = pimPage.getResultTableRowsCount();

        // Realizamos la aserción que provocará un Fail porque `results` será 1 a diferencia de lo esperado (>1)
        Assert.assertTrue(results > 1, "Se esperaba que se mostrarán varios registros repetidos del empleado, pero solo se mostró: " + results);
    }

    // TC: P-PIM-02P
    @Test
    public void addEmployeeTest_P_PIM_02P()
    {
        // Datos de prueba
        String firstName = "Peter";
        String lastName = "Mc Donald";
        String employeeId = "0669";
        String fullName = firstName + " " + lastName;

        // Paso 1: El usuario hace click en "Add Employee"
        pimPage.clickAddEmployeeTab();

        // Paso 2: El usuario ingresa nombre del empleado.
        pimPage.setFirstNameValue(firstName);

        // Paso 3: El usuario ingresa apellido del usuario.
        pimPage.setLastNameValue(lastName);

        // Paso 4: El usuario ingresa otro ID.
        pimPage.setAddEmployeeIdValue(employeeId);

        // Paso 5: El usuario hace click en "Save"
        pimPage.clickSaveAddEmployeeButton();

        // Esperamos a que se guarde la info y la vista se refresque hacia los detalles personales
        pimPage.waitForPersonalDetailsRefresh();

        // Paso 6: El usuario hace click en "Employee List"
        pimPage.clickEmployeeListButton();

        // Paso 7: El usuario ingresa el nombre completo en "Employee Name"
        pimPage.setEmployeeNameValue(fullName);

        // Paso 8: El usuario selecciona al empleado en el dropdown.
        pimPage.selectFirstEmployeeNameOption();

        // Finalmente, el usuario busca:
        pimPage.clickSearchButton();
        pimPage.waitForEmployeeListContainer();

        // Validamos que el resultado esperado es encontrar al nuevo empleado
        int results = pimPage.getResultTableRowsCount();
        Assert.assertTrue(results >= 1, "Se esperaba que el empleado recién creado (" + fullName + ") apareciera en la lista, pero se encontraron " + results + " resultados.");
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
