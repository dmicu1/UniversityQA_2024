package com.hiberus.ejercicios.run;

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

import static org.junit.Assert.assertEquals;

public class Agregar3productosalcarito {
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

        //PRC-Login para pagina Sauce
        //Paso1. Ir a la página https://www.saucedemo.com
        driver.get("https://www.saucedemo.com");

        //Paso2. Escribir el username standard_user
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        //Paso3. Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        //Paso4. Pulsar en el botón del Login.
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();
    }

    @Test
    public void testAgregarProductosAlCarrito() {
        agregarProductosAlCarrito();

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        int itemCount = Integer.parseInt(cartIcon.getText());
        assertEquals(3, itemCount);
        System.out.println("Cantidad de productos agregados al carrito: " + itemCount);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void agregarProductosAlCarrito() {
        String[] productos = {
                "#add-to-cart-sauce-labs-backpack",
                "#add-to-cart-sauce-labs-bike-light",
                "#add-to-cart-sauce-labs-bolt-t-shirt",
                "#add-to-cart-sauce-labs-fleece-jacket",
                "#add-to-cart-sauce-labs-onesie",
                "#add-to-cart-test"
        };
        // Estrategia seleccion producto
        for (int i = 0; i < 3; i++) {
            WebElement addToCartButton = driver.findElement(By.cssSelector(productos[i]));
            addToCartButton.click();
        }
    }
}
