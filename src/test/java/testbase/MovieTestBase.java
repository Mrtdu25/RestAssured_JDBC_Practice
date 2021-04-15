package testbase;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class MovieTestBase {

    @BeforeAll
    public static void setUp(){
       //
        RestAssured.baseURI="http://www.omdbapi.com";


    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
    }


}
