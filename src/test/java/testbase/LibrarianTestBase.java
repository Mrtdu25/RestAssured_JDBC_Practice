package testbase;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
import utils.ConfigurationReader;

public class LibrarianTestBase {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("library.baseURI");
        basePath = ConfigurationReader.getProperty("library.basePath");
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }
}
