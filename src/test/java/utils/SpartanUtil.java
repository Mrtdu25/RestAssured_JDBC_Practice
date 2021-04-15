package utils;

import com.github.javafaker.Faker;
import pojooooo.Spartan;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    static Faker faker=new Faker();

    public static Map<String,Object> getRandomSpartanMap(){

        Map<String ,Object> singleSpartanMap=new LinkedHashMap<>();

        singleSpartanMap.put("name",faker.name().firstName());
        singleSpartanMap.put("gender",faker.demographic().sex());
        singleSpartanMap.put("phone",faker.number().numberBetween(5000000000L,9999999999L));

        return singleSpartanMap;

    }


    public static Spartan getRandomSpartanAsObject(){

        Spartan spartanPayload=new Spartan();

        spartanPayload.setName(faker.name().firstName());
        spartanPayload.setGender(faker.demographic().sex());
        spartanPayload.setPhone(faker.number().numberBetween(5000000000L,9999999999L));

        return spartanPayload;

    }



}
