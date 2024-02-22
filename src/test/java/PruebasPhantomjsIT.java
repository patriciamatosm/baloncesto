import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebasPhantomjsIT {
    private static WebDriver driver = null;

    @Test
    public void tituloIndexTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(),
                "El titulo no es correcto");
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }

    @Test
    public void pfATest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        // Pulsar el botón "Poner votos a cero" en la página principal
        WebElement botonPonerVotosACero = driver.findElement(By.id("votos_a_cero"));
        botonPonerVotosACero.click();

        // Esperar un momento para que la página se actualice
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Pulsar el botón "Ver votos" en la página principal
        WebElement botonVerVotos = driver.findElement(By.id("ver_votos"));
        botonVerVotos.click();

        // Esperar un momento para que la página se actualice
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    
        WebElement paragraphElement = driver.findElement(By.id("stringName"));
        String actualText = paragraphElement.getText();

        String expectedText = "Jugadores: n:Llull v:0 n:Rudy v:0 n:Tavares v:0";
        assertEquals(expectedText, actualText, "Text from the paragraph does not match the expected value");


        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }

    @Test
    public void pfBTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        WebElement nombreJugadorInput = driver.findElement(By.id("txtOtros"));
        nombreJugadorInput.sendKeys("Javier");

        // Seleccionar la opción "Otro"
        WebElement opcionOtro = driver.findElement(By.id("otrosRadio"));
        opcionOtro.click();

        // Pulsar el botón "Votar"
        WebElement botonVotar = driver.findElement(By.id("votar"));
        botonVotar.click();


        // Pulsar el botón "Ver votos" en la página principal
        WebElement botonVerVotos = driver.findElement(By.id("ver_votos"));
        botonVerVotos.click();

        // Esperar un momento para que la página se actualice
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    
        WebElement paragraphElement = driver.findElement(By.id("stringName"));
        String actualText = paragraphElement.getText();

        String expectedText = "Jugadores: n:Llull v:0 n:Rudy v:0 n:Tavares v:0 n:Patricia v:1 n:Javier v:1";
        assertEquals(expectedText, actualText, "Text from the paragraph does not match the expected value");


        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }
}
