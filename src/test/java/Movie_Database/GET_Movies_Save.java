package Movie_Database;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.MovieTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GET_Movies_Save extends MovieTestBase {



    //http://www.omdbapi.com/?t=Street Fight&apikey=a9f54578&r=JSON
    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie() {

        JsonPath jp = given()
                .queryParam("apiKey", "5b5d0fe8")
                .queryParam("t", "Hannibal").

                        when()
                .get().prettyPeek().  // our request URL is already complete , do not need to add anything here
                then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title", is("Hannibal"))
                .body("Ratings[0].Source", is("Internet Movie Database"))
                .extract().jsonPath();

//        MoviePojo hannibalInfo = jp.getObject("", MoviePojo.class);
//        System.out.println(hannibalInfo);

        String hannibalInfo = jp.getString("");
       System.out.println(hannibalInfo);

        String title = jp.getString("Title");
        System.out.println(title);

    }
}
