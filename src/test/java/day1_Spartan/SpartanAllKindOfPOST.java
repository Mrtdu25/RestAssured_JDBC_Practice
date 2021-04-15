package day1_Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import testbase.SpartanTestBaseAdmin;
import utils.SpartanUtil;
import java.io.File;

public class SpartanAllKindOfPOST extends SpartanTestBaseAdmin {

    @DisplayName("Spartan POST Request with String")
    @Test
    public void postRequestWithString() {

        String newSpartanStr =  "    {\n" +
                "        \"name\": \"Nikita\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 9876565266\n" +
                "    }" ;

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(newSpartanStr).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is(equalTo("A Spartan is Born!")));

    }


    @DisplayName("Spartan POST Request with String")
    @Test
    public void postRequestWithMap() {

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMap()).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is(equalTo("A Spartan is Born!")));
    }



    @DisplayName("Spartan POST Request with String")
    @Test
    public void postRequestWithCSV() {
        File file=new File("SingleSpartanPayLoad.csv");
        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(file).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is(equalTo("A Spartan is Born!")));
    }


    @DisplayName("Spartan POST Request with String")
    @Test
    public void postRequestWithPOJO() {

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanAsObject()).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is(equalTo("A Spartan is Born!")));
    }




}
