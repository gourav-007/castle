package com.castle.utils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JiraUtils {

    private JiraUtils(){}


    @Test
    public static void createJiraIssue(){
        String body = "{\n" +
                "  \"fields\": {\n" +
                "    \"project\":\n" +
                "    {\n" +
                "      \"key\": \"DEM\"\n" +
                "    },\n" +
                "    \"summary\": \"My First Jira issue through REST\",\n" +
                "    \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                "    \"issuetype\": {\n" +
                "      \"name\": \"Bug\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Response response = given()
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .header("Authorization","Basic Z291cmF2MDcxMjk1OkppcmFAMjAyNA==")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");

        System.out.println(response.prettyPrint());

    }
}
