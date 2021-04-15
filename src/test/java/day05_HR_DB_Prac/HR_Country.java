package day05_HR_DB_Prac;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojooooo.Country;
import testbase.HR_DB_TestBase;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import utils.DB_Utility;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.* ;

public class HR_Country extends HR_DB_TestBase {

    @DisplayName("GET All countries")
    @Test
    public void getCountries() {

        Response response = get("/countries").prettyPeek();

        JsonPath jp = response.jsonPath();

        List<String> allCountry_id = jp.getList("items.country_id");

        System.out.println(allCountry_id);

        DB_Utility.runQuery("SELECT * FROM COUNTRIES");
        List<String> country_id = DB_Utility.getColumnDataAsList("COUNTRY_ID");
        System.out.println(country_id);

        assertThat(allCountry_id,is(equalTo(country_id)));


    }

    @DisplayName("GET All countries")
    @Test
    public void getCountries2() {

        JsonPath jp = given()
                .log().all()
                .accept(ContentType.JSON).
                        when()
                .get("/countries").
                        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        List<Country> allCountries = jp.getList("items", Country.class);
        allCountries.forEach(System.out::println);
        System.out.println(allCountries.size());

        DB_Utility.runQuery("SELECT * FROM COUNTRIES");
        List<Map<String, Object>> allDataAsListOfMap = DB_Utility.getAllDataAsListOfMap();

        for (int i = 0; i <=24; i++) {
            assertThat(allCountries.get(i).getCountry_id(),is(equalTo(allDataAsListOfMap.get(i).get("COUNTRY_ID"))));
            assertThat(allCountries.get(i).getCountry_name(),is(equalTo(allDataAsListOfMap.get(i).get("COUNTRY_NAME"))));
            assertThat(allCountries.get(i).getRegion_id(),is(equalTo(allDataAsListOfMap.get(i).get("REGION_ID"))));
        }



    }
}
