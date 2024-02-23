package com.hiberus.ejercicios.run;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.util.StringList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class odenarAlfabeticamente {
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
    public void testOrdenAlfabetico() {

        // Seleccionar el filtro NAME (Z TO A)
        WebElement filterDropdown = driver.findElement(By.className("product_sort_container"));
        filterDropdown.click();
        WebElement filterOption = driver.findElement(By.xpath("//select[@class='product_sort_container']//child::option[@value='za']"));
        filterOption.click();

        List<WebElement> list= driver.findElements(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item']//descendant::div[@class='inventory_item_name']"));
        List<String> listZtoA=new ArrayList<>();
        for(WebElement producto : list ){
         listZtoA.add(producto.getText());
        }
        List<String> listaordenada=new ArrayList<>(listZtoA);
        Collections.sort(listaordenada,Collections.reverseOrder());

        List<WebElement> actualItemsZA=driver.findElements(By.className("inventory_item"));
        Assert.assertTrue(listaordenada.equals(listZtoA));

    }

    @After
        public void tearDown() {
          // driver.quit();
        }


    }

