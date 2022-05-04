package api;

import api.speciaication.Specifications;
import io.restassured.response.Response;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.virtualauthenticator.Credential;
import сonfProperties.ConfProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Api {
    private final static String URL = "https://www.reddit.com";
    private final static String OAUTH_URL = "https://oauth.reddit.com";
    private final static String USER_NAME = "F1x_Sergo";
    private final static String PASSWORD = ConfProperties.getProperty("passwordAPI");
    private final static String CLIENT_ID = "0kuG5PT7qBZg9VBpFHT1oA";
    private final static String SECRET_KEY = ConfProperties.getProperty("secret_Key");


    @Test
    public void getAccessToken() {
        Specifications.instalSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        Map<String, String> auth = new HashMap<>();
        auth.put(CLIENT_ID, SECRET_KEY);
        Map<String, String> user = new HashMap<>();
        user.put("grant_type", "password");
        user.put("username", USER_NAME);
        user.put("password", PASSWORD);

        given()
                .header(CLIENT_ID, SECRET_KEY)
                .body(user)
                .when()
                //.post("/api/v1/access_token")
                .get("/api/v1/me")
                .then().log().all()
                .extract().response();
    }
}
