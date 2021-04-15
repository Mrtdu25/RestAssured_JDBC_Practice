package testbase;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigurationReader;

public class SpartanTestBaseMrt {

    @BeforeAll
    public static void setUp(){

      baseURI= ConfigurationReader.getProperty("spartanBaseUrl");
      basePath=ConfigurationReader.getProperty("spartanBasePath");

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }



}
