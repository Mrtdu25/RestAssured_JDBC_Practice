package day1_Spartan;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import testbase.SpartanTestBaseMrt;

import java.util.List;

public class GETSingleSpartan extends SpartanTestBaseMrt {

    @DisplayName("Get a single spartan use PATH PARAM")
    @Test
    public void getSingleSpartan(){

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("id", 140).
                        when()
                .get("/spartans/{id}").prettyPeek();

        assertThat(response.statusCode(),is(200));

        JsonPath jp = response.jsonPath();

        assertThat(jp.getInt("id"),is(equalTo(140)));
        assertThat(jp.getString("name"),is(equalTo("50 cent")));
        assertThat(jp.getString("gender"),is(equalTo("Male")));
        assertThat(jp.getInt("phone"),is(equalTo(1234566541)));

    }


    @DisplayName("Get spartans by using quesry param filter data by gender Male and the name contains 'du'")
    @Test
    public void getSpartansByFiltering(){

        JsonPath jp = given()
                .log().all()
                .accept(ContentType.JSON)
                .queryParam("nameContains", "du")
                .queryParam("gender", "Male").
                        when()
                .get("/spartans/search").
                        then()
                .log().all()
                .assertThat()
                .statusCode(is(equalTo(200)))
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        List<Object> names = jp.getList("content.name");
        System.out.println(names);

        assertThat(jp.getInt("content.id[0]"),is(109));
        assertThat(jp.getString("content.name[0]"),is("Abdulhamid"));
        assertThat(jp.getString("content.gender[0]"),is("Male"));


    }


    @DisplayName("GET single Spartan that is Not Available and verify status code 404 and body has Not Found")
    @Test
    public void getRequestNotFound404(){

        given()
                .log().all()
                .pathParam("id",500)
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(equalTo(404)))
                 .body("error",is(equalTo("Not Found")))
                 .body("message",is(equalTo("")))
                ;
    }

    @DisplayName("Testing /api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartanXML() {

        /**
         * given
         *      --- RequestSpecification
         *      used to provide additional information about the request
         *      base url  base path
         *      header , query params , path variable , payload
         *      authentication authorization
         *      logging , cookie
         * when
         *      --- This is where you actually send the request with http method
         *      -- like GET POST PUT DELETE .. with the URL
         *      -- We get Response Object after sending the request
         * then
         *      -- ValidatableResponse
         *      -- This is where we can do validation
         *      -- validate status code , header , payload , cookie
         *      -- responseTime , structure of the payload , logging response
         */

        given()
                .log().all()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
       then()
                .log().all()
                .statusCode(is(equalTo(200)))
                .contentType(ContentType.XML);

    }


}
