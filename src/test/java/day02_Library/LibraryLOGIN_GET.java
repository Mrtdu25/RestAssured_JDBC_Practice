package day02_Library;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import testbase.LibrarianTestBase;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class LibraryLOGIN_GET extends LibrarianTestBase {

  private static String myToken;

    @DisplayName("Testing POST /login Endpoint")
    @Test
    public void testLogin()  {
        /*
        Librarian1  email	librarian69@library
        Librarian1  password		KNPXrm3S
         */
      myToken = given()
              .log().all()
              .contentType(ContentType.URLENC)
              .formParam("email", "librarian69@library")
              .formParam("password", "KNPXrm3S").
                      when()
              .post("/login").
                      then()
              .log().all()
              .contentType(ContentType.JSON)
              .statusCode(is(200))
              .body("token", is(notNullValue()))
              .extract()
              .jsonPath()
              .getString("token");
      

    }

  @DisplayName("GET Request")
  @Test
  public void name() {

    JsonPath jp = given()
            .log().all()
            .accept(ContentType.JSON)
            .header("x-library-token", myToken).
                    when()
            .get("/dashboard_stats").
                    then()
            .log().all()
            .contentType(ContentType.JSON)
            .statusCode(is(200))
            .extract()
            .jsonPath();

    assertThat(jp.getInt("book_count"), is(equalTo(1171)));
    assertThat(jp.getInt("borrowed_books"), is(equalTo(653)));
    assertThat(jp.getInt("users"), is(equalTo(7432)));

  }

}
