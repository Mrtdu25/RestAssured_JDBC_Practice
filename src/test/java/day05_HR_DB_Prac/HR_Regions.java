package day05_HR_DB_Prac;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojooooo.Regions;
import testbase.HR_DB_TestBase;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import utils.DB_Utility;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.* ;
public class HR_Regions extends HR_DB_TestBase {

    @DisplayName("Testing /regions/{region_id}")
    @Test
    public void name() {
        JsonPath jp = given()
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON).
                        when()
                .get("/regions").
                        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath();

        List<Regions> allRegions = jp.getList("items", Regions.class);
        allRegions.forEach(System.out::println);

        DB_Utility.runQuery("SELECT REGION_ID,REGION_NAME FROM REGIONS");

        List<Map<String, Object>> allDataAsListOfMap = DB_Utility.getAllDataAsListOfMap();
        allDataAsListOfMap.forEach(System.out::println);

        assertThat(allRegions.get(1).getRegion_id(),is(equalTo(allDataAsListOfMap.get(1).get("REGION_ID"))));
        assertThat(allRegions.get(1).getRegion_name(),is(equalTo(allDataAsListOfMap.get(1).get("REGION_NAME"))));

        assertThat(allRegions.get(2).getRegion_id(),is(equalTo(allDataAsListOfMap.get(2).get("REGION_ID"))));
        assertThat(allRegions.get(2).getRegion_name(),is(equalTo(allDataAsListOfMap.get(2).get("REGION_NAME"))));

        assertThat(allRegions.get(3).getRegion_id(),is(equalTo(allDataAsListOfMap.get(3).get("REGION_ID"))));
        assertThat(allRegions.get(3).getRegion_name(),is(equalTo(allDataAsListOfMap.get(3).get("REGION_NAME"))));

        assertThat(allRegions.get(0).getRegion_id(),is(equalTo(allDataAsListOfMap.get(0).get("REGION_ID"))));
        assertThat(allRegions.get(0).getRegion_name(),is(equalTo(allDataAsListOfMap.get(0).get("REGION_NAME"))));

    }
}
