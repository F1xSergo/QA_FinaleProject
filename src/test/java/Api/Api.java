package Api;

import Api.Speciaication.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Api {
    private final static String URL = "https://www.reddit.com";

    @Test
    public void successUserIdentity() {
        Specifications.instalSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
        String token = "sPmMT4T9UINoqzvPErzVCArkPH-t9Q";
//0kuG5PT7qBZg9VBpFHT1oA

        Map<String, String> user = new HashMap<>();
        user.put("client_id", "0kuG5PT7qBZg9VBpFHT1oA");
        //user.put("redirect_uri", "https://www.reddit.com");
        //вариант без Response
//            given()
//                    .body(user)
//                    .when()
//                    .post("/api/v1/me")
//                    .then().log().all();
//                    .body("id",  equalTo(4))
//                    .body("token", equalTo( "sPmMT4T9UINoqzvPErzVCArkPH-t9Q"));
        //вариант c Response
        Response response = given()
                .body(user)
                .when()
                .get("/api/v1/me")
                //.post("/api/v1/access_token")
                .then().log().all()
                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        String owner = jsonPath.get("owner");
//        System.out.println(owner);
        //Assertions.assertEquals("sPmMT4T9UINoqzvPErzVCArkPH-t9Q", token);
    }

    @Test
    public void getAuthorizationUser() {
        Specifications.instalSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
//0kuG5PT7qBZg9VBpFHT1oA

        Map<String, String> user = new HashMap<>();
        user.put("client_id", "0kuG5PT7qBZg9VBpFHT1oA");
        user.put("response_type", "code");
        user.put("redirect_uri", "https://www.reddit.com/prefs/apps");

        user.put("client_secret", "sPmMT4T9UINoqzvPErzVCArkPH-t9Q");
        user.put("grant_type", "authorization_code&code=CODE&redirect_uri='https://www.reddit.com'");

        //вариант без Response
//            given()
//                    .body(user)
//                    .when()
//                    .post("/api/v1/me")
//                    .then().log().all();
//                    .body("id",  equalTo(4))
//                    .body("token", equalTo( "sPmMT4T9UINoqzvPErzVCArkPH-t9Q"));
        //вариант c Response
        Response response = given()
                .body(user)
                .when()
                .post("/api/v1/authorize.compact?")
                //.post("/api/v1/access_token")
                .then().log().all()
                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        String token = jsonPath.get("access_token");
//        System.out.println(token);
        //Assertions.assertEquals("sPmMT4T9UINoqzvPErzVCArkPH-t9Q", token);
    }
}
