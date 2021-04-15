package day04_Serilization_Deserilization;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojooooo.Spartan;
import testbase.SpartanTestBaseAdmin;
import utils.DB_Utility;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StoringResponseIntoMap_and_POJO extends SpartanTestBaseAdmin {

    @DisplayName("GET Request")
    @Test
    public void getSingleSpartanSaveTheResponse() {

        JsonPath jp = givenSpec
                .accept(ContentType.JSON)
                .pathParam("id", 887).
                        when()
                .get("/spartans/{id}").
                        then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(is(200))
                .body("id", is(equalTo(887)))
                .body("name", is(equalTo("Tayfun")))
                .body("gender", is(equalTo("Male")))
                .body("phone", is(equalTo(9879879870L)))
                .time(lessThan(5L), TimeUnit.SECONDS)
                .extract()
                .jsonPath();


        Map<Object, Object> mapOfId887 = jp.getMap("");
        System.out.println(mapOfId887);

        Spartan spartanID887 = jp.getObject("", Spartan.class);
        System.out.println(spartanID887);

       assertThat(mapOfId887.get("name"), is(equalTo(spartanID887.getName())));
       assertThat(mapOfId887.get("gender"), is(equalTo(spartanID887.getGender())));
       assertThat(mapOfId887.get("phone"), is(equalTo(spartanID887.getPhone())));


    }

    @DisplayName("GET All spartans")
    @Test
    public void getAllSpartansAndSave() {

        JsonPath jp = givenSpec
                .accept(ContentType.JSON).
                        when()
                .get("/spartans").
                        then()
                .log().all()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(is(200))
                .extract()
                .jsonPath();

//        List<Object> listOfAllSpartan = jp.getList("");
//        listOfAllSpartan.forEach(System.out::println);

        System.out.println("===================================================================");

        List<Spartan> listOfAllSpartanpojo = jp.getList("", Spartan.class);
        listOfAllSpartanpojo.forEach(System.out::println);

    }
}
