package day1_Spartan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

// Hamcrest assertion library already part of
// our RestAssured Dependency in pom file
// No separate dependency needed
public class HamcrestMatchersTest {

    @DisplayName("Conditional ")
    @Test
   public void name() {

        int num=5;

        assertThat(5,is(equalTo(num)));
        assertThat(num,greaterThan(3));
        assertThat(num,is(notNullValue()));
        assertThat(num,is(not(equalTo(4))));
        assertThat(num,is(lessThan(10)));

    }



    @DisplayName("Common Matchers for Strings")
    @Test
    public void testString(){

     String str="I like to eat meat";

     assertThat(str,containsString("meat"));
     assertThat(str,endsWith("meat"));
     assertThat(str,startsWith("I like"));

     assertThat(str,equalToIgnoringCase("I LIKE to EAT meat"));

     assertThat(str,containsStringIgnoringCase("lIkE"));


    }







}
