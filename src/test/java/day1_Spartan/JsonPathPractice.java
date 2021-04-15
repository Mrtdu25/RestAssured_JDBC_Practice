package day1_Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;

import testbase.SpartanTestBaseAdmin;

import java.util.List;

public class JsonPathPractice extends SpartanTestBaseAdmin {

    @DisplayName("GET ALL SPARTANS WITH BASIC AUTH AND FIND AND SAVE WITH JSON PATH")
    @Test
    public void name() {
        Response response = given()
                .log().all()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .queryParam("nameContains", "g").
                        when()
                .get("/spartans/search").prettyPeek();

        JsonPath jp = response.jsonPath();

        List<Object> allSpartanContainsG = jp.getList("content");

        allSpartanContainsG.forEach(System.out::println);
        System.out.println("allSpartanContainsG.size() = " + allSpartanContainsG.size());


    }
}
