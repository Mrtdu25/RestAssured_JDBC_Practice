package pojooooo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employees {

    private String employee_id;
    private String first_name;
    private String last_name;
    private String salary;

}

/*
            "email": "SKING",
            "phone_number": "515.123.4567",
            "hire_date": "2003-06-17T04:00:00Z",
            "job_id": "AD_PRES",
            "salary": 24000,
            "commission_pct": null,
            "manager_id": null,
            "department_id": 90,
 */