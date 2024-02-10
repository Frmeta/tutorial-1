package id.ac.ui.cs.advprog.eshop.functional;


import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }
    @Test
    void createProductIsCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        // Klik tombol product
        WebElement checkInput = driver.findElement(By.id("product"));
        checkInput.click();

        // Klik tombol create
        checkInput = driver.findElement(By.id("create"));
        checkInput.click();

        // isi nama produk
        WebElement inputField = driver.findElement(By.id("nameInput"));
        inputField.clear();
        inputField.sendKeys("Laptop");

        // isi kuantiti produk
        inputField = driver.findElement(By.id("quantityInput"));
        inputField.clear();
        inputField.sendKeys("123");

        // submit
        WebElement nameInput = driver.findElement(By.id("submit"));
        nameInput.click();

        // Cek apakah berhasil tersimpan
        WebElement firstProduct = driver.findElement(By.className("productName"));
        assertEquals(firstProduct.getText(), "Laptop", "Check if the product is saved");
    }

}