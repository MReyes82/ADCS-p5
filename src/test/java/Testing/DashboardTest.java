package Testing;

import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest
{
    private WebDriver driver;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        var loginPage = new LoginPage(driver, 5);
        dashboardPage = new DashboardPage(driver, 10, loginPage);
        // Posicionamos el url segun lo especicado en las precondiciones
        dashboardPage.accessToDashboard();
    }

    // Definimos dentro de un solo metodo con etiqueta @Test ambos test especificados en la matriz
    // En realidad, solo los separamos de manera que se pueden ejecutar ambos TC ejecutando el mismo
    // metodo 2 veces. Para saber cual es cual, se imprime en la consola.
    // Verificamos de manera dinamica que se completara usando Asserts para verificar que se redirige a la url correcta despues de cada punch in o punch out,
    // dependiendo del header text que se obtiene al inicio del test.
    @Test
    public void testDashboard()
    {
        // navegar a la pagina de asistencia
        dashboardPage.clickClockButton();
        // esperamos a que la pagina termine de cargar
        //dashboardPage.waitForAttendanceSection();
        // obtenemos el header para saber cual metodo ejecutar
        String headerText = dashboardPage.getPunchHeaderText();

        for (int i = 0 ; i < 2 ; i++)
        {
            if (headerText.equals("Punch In"))
            {
                punchInTest("Punch in test");
                System.out.println("[SE MARCO LA ENTRADA]");
                dashboardPage.waitForAttendanceSection("punchOut");
                Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/attendance/punchOut");
            }
            else
            {
                punchOutTest("Punch out test");
                System.out.println("[SE MARCO LA SALIDA]");
                dashboardPage.waitForAttendanceSection("punchIn");
                Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/attendance/punchIn");
            }
        }
    }

    public void punchInTest(String note)
    {
        // El sistema asigna automaticamente una fecha y hora que no colisionan
        // A si que no es necesario mandar una llave, solo llenar el text field
        // de la nota
        dashboardPage.setNoteTextFieldValue(note);
        dashboardPage.clickSubmitButton();
    }

    public void punchOutTest(String note)
    {
        // El sistema asigna automaticamente una fecha y hora que no colisionan
        // A si que no es necesario mandar una llave, solo llenar el text field
        // de la nota
        /*dashboardPage.setDatePickerValue(date);
        dashboardPage.setTimePickerValue(time);*/
        dashboardPage.setNoteTextFieldValue(note);
        dashboardPage.clickSubmitButton();
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
            driver.quit();
    }
}
