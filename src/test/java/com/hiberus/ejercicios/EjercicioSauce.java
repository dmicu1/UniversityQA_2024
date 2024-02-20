package com.hiberus.ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EjercicioSauce {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void EjercicioSauce (){


        driver.get("https://www.saucedemo.com");
        String title = driver.getTitle();
        System.out.println(title);
        int titleLength = title.length();
        System.out.println(titleLength);
        //Ejercicio2


    }
@Test
public  void LoginSauce(){

    //Paso1. Ir a la página https://www.saucedemo.com
driver.get("https://www.saucedemo.com");

    //Paso2. Escribir el username standard_user
    WebElement inputUsername=driver.findElement(By.id("user-name"));
    inputUsername.sendKeys("standard_user");

    //Paso3. Escribir el password secret_sauce
    WebElement inputPassword=driver.findElement(By.id("password"));
    inputPassword.sendKeys("secret_sauce");

    //Paso4. Pulsar en el botón del Login.
WebElement buttonLogin=driver.findElement(By.id("login-button"));
buttonLogin.click();

    //Paso5. Validar que hemos accedido correctamente a la página
    driver.get("https://www.saucedemo.com/inventory.html");
    String currentUrl = driver.getCurrentUrl();
    if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
        System.out.println("Se ha accedido correctamente a la página.");
    } else {
        System.out.println("No se ha accedido correctamente a la página.");
    }

}
    @After
    public void tearDown() {
        driver.close();
    }
}