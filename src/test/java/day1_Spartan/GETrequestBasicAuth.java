package day1_Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import testbase.SpartanTestBaseAdmin;
import utils.ConfigurationReader;

public class GETrequestBasicAuth extends SpartanTestBaseAdmin {

    String username= ConfigurationReader.getProperty("spartan.admin.username");
    String password= ConfigurationReader.getProperty("spartan.admin.password");

    @DisplayName("GET Single spartan using basic auth ")
    @Test
    public void getSingleSpartanWithBasicAuth() {

        given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic(username,password)
                .pathParam("id",580).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON);


    }
}
