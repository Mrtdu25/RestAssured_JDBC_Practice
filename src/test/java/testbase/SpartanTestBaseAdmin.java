package testbase;
import static io.restassured.RestAssured.*;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigurationReader;
public class SpartanTestBaseAdmin {
public static RequestSpecification givenSpec;
    @BeforeAll
    public static void setUp(){

        baseURI= ConfigurationReader.getProperty("spartan.base_url");
        basePath=ConfigurationReader.getProperty("spartan.base_path");
        givenSpec = given().log().all().auth().basic("admin", "admin");

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

}
