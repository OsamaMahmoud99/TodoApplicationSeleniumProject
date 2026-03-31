package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.User;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.util.List;

public class RegisterApi {

    private static List<Cookie> resrAssuredCookies;

    private static String accessToken;

    private static String userId;

    private static String firstName;

    public  String getUserId() {
        return userId;
    }

    public String getToken() {
        return accessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Cookie> getCookies() {
        return resrAssuredCookies;
    }

    public void register() {

        User user = new UserUtils().generateRandomUser();
        Response response = given()
                .baseUri("https://todo.qacart.com/")
                .header("Content-Type", "application/json")
                .body(user)
                .log().all()

                .when()
                    .post(EndPoint.API_REGISTER_ENDPOINT)
                .then()
                    .log().all()
                    .extract().response();

        if(response.statusCode() != 201) {
            throw new RuntimeException("Something went wrong with the request");
        }
        resrAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userId = response.path("userID");
        firstName = response.path("firstName");

    }
}
