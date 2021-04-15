package day3_CRUD;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import org.junit.jupiter.api.TestMethodOrder;
import testbase.SpartanTestBaseAdmin;
import utils.SpartanUtil;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CRUD_Practice extends SpartanTestBaseAdmin {

    private static int myID;


    @DisplayName("1.POST Create single spartan")
    @Test
    public void createSingleSpartan() {

        JsonPath jp = givenSpec.contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanAsObject()).
                when()
                .post("/spartans").
                        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .assertThat()
                .extract()
                .jsonPath();

        myID= jp.getInt("data.id");
        System.out.println(myID);
    }

    @DisplayName("2. GET the Spartan that have been created previous step")
    @Test
    public void getSpartanWithMyID() {

        givenSpec
                .pathParam("id",myID)
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(is(200));
    }


    @DisplayName("3.PUT  Update the request that you just created")
    @Test
    public void updateSpartanWithMyID() {

        givenSpec
                .pathParam("id",myID)
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMap()).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(204));


    }

    @DisplayName("4. GET the Spartan that have been updated previous step")
    @Test
    public void getSpartanAgainWithMyID() {

        givenSpec
                .pathParam("id",myID)
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(is(200));
    }

    @DisplayName("5. DELETE spartan that you Updated")
    @Test
    public void deleteSpartanWithMyID() {

        givenSpec
                .pathParam("id",myID).
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204));

    }

    @DisplayName("6. GET the Spartan that have been deleted previous step")
    @Test
    public void getSpartanWithMyIDLastTime() {

        givenSpec
                .pathParam("id",myID)
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(is(404));
    }


}
