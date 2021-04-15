package day05_HR_DB_Prac;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojooooo.Employees;
import testbase.HR_DB_TestBase;
import io.restassured.path.json.JsonPath;
import utils.DB_Utility;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.* ;

public class HR_Employees extends HR_DB_TestBase {

    @DisplayName("GET a Single Spartan ")
    @Test
    public void testThirdRegionIsAsia(){

        Response response = get("/employees").prettyPeek();
        //response.then().log().all();

        JsonPath jp = response.jsonPath();


        for (int i = 0; i <24; i++) {
            List<Employees> lisOfEmployees = jp.getList("items", Employees.class);
            Employees emp1 = lisOfEmployees.get(i);
            System.out.println(emp1);


            DB_Utility.runQuery("SELECT employee_id,first_name,last_name,salary FROM EMPLOYEES");
            Map<String,Object> rowMap = DB_Utility.getRowMap(i+1);
            System.out.println(rowMap);

            assertThat(emp1.getEmployee_id(),is(equalTo(rowMap.get("EMPLOYEE_ID"))));
            assertThat(emp1.getFirst_name(),is(equalTo(rowMap.get("FIRST_NAME"))));
            assertThat(emp1.getLast_name(),is(equalTo(rowMap.get("LAST_NAME"))));
            assertThat(emp1.getSalary(),is(equalTo(rowMap.get("SALARY"))));

        }

















    }
}
