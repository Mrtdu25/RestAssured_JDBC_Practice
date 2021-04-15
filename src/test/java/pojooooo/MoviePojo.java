package pojooooo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor @NoArgsConstructor @Getter@Setter@ToString
public class MoviePojo {
    private String Title;
    private String Year;
    private String Released;
    private String Genre;
    private String Director;
    private String Actors;
    private String Language;
    private String Country;
    private String Awards;

    /*
    "Title": "Hannibal",
    "Year": "2001",
    "Released": "09 Feb 2001",
    "Genre": "Crime, Drama, Thriller",
    "Director": "Ridley Scott",
    "Actors": "Anthony Hopkins, Julianne Moore, Gary Oldman, Ray Liotta",
    "Language": "English, Italian, Japanese",
    "Country": "USA, UK, Italy",
    "Awards": "9 wins & 25 nominations.",
     */


}
