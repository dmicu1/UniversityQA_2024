package com.hiberus.ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
public class Ejerciciodmicu1 {

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
            WebElement inputUsername=driver.findElement(By.id("user-name"));
            inputUsername.sendKeys("standard_user");

            //Paso3. Escribir el password secret_sauce
            WebElement inputPassword=driver.findElement(By.id("password"));
            inputPassword.sendKeys("secret_sauce");

            //Paso4. Pulsar en el botón del Login.
            WebElement buttonLogin=driver.findElement(By.id("login-button"));
            buttonLogin.click();
        }


    @Test public void Sauce(){

        driver.get("https://www.saucedemo.com");
        String title = driver.getTitle();
        System.out.println(title);
        int titleLength = title.length();
        System.out.println(titleLength);
    }

@Test public void LoginSauce(){

    //Paso5. Validar que hemos accedido correctamente a la página
    driver.get("https://www.saucedemo.com/inventory.html");
    String currentUrl = driver.getCurrentUrl();
    if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
        System.out.println("Se ha accedido correctamente a la página.");
    } else {
        System.out.println("No se ha accedido correctamente a la página.");
    }
}

//Inventario TS
@Test public void validarElNumeroDeResultados(){
    //Paso5. Validar que el número de productos mostrados es igual a 6
    java.util.List<WebElement> productList = driver.findElements(By.className("inventory_item"));
    if (productList.size() == 6) {
        System.out.println("El número de productos mostrados es igual a 6.");
    } else {
        System.out.println("El número de productos mostrados NO es igual a 6.");
           assertEquals(6, productList.size());
    System.out.println("La lista contiene 6 items");
    }
    assertEquals(6, productList.size());
    System.out.println("La lista contiene 6 items");
}
@Test public void incrementoDelValorDelCarrito(){
   //Paso 5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt
    WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
    addToCartButton.click();

    // Paso6. Validamos que en el icono del carrito se ha agregado el valor 1.
    //Localizar el carrito
   WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));

// Validar que se ha agregado 1 producto
    String cartItemCount = cartIcon.getText();
    if (cartItemCount.equals("1")) {
        System.out.println("Se ha agregado correctamente 1 producto al carrito.");
    } else {
        System.out.println("No se ha agregado ningún producto al carrito o el valor es incorrecto.");
    }

}
@Test public void validarElBotonEliminar(){
    //Paso5. Agregar al carrito el producto Sauce Labs Onesie
WebElement addToCardButton=driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
addToCardButton.click();
    //Paso6. Validamos que, al agregar el producto, se visualiza el botón REMOVE
    if (driver.findElement(By.id("remove-sauce-labs-onesie")).isDisplayed()) {
        System.out.println("Se visualiza correctamente el botón 'REMOVE' después de agregar el producto.");
    } else {
        System.out.println("No se visualiza el botón 'REMOVE' después de agregar el producto.");
    }
}


@Test public void EliminarProductoDelCarito(){
    try {
        WebElement addToCardButon = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addToCardButon.click();

        WebElement removeToCardButon = driver.findElement(By.id("remove-sauce-labs-onesie"));
        removeToCardButon.click();

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        String cartItemCount = cartIcon.getText();

        assert cartItemCount.isEmpty() : "La cesta esta vacia.";
        System.out.println("El icono de la cesta está limpio, no hay ningún producto agregado.");
    } catch (NoSuchElementException nsee) {
        System.err.println("No se encontró un elemento esperado: " + nsee.getMessage());
    }
}






    @After
    public void tearDown() {
        driver.close();
    }
}