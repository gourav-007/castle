package com.castle.test;
import com.castle.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class GetTests {

    @Test
    public void getEmployeeDetails(){
        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .get("/students");

        System.out.println(response.prettyPrint());

        assertThat(response.getStatusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .as("Validating the size of an array response.")
                .isLessThan(30);
    }


    @Test(dataProvider = "getData")
    public void getEmployeeDetail(Integer id, String lastName){
        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .pathParam("id",id)
                .get("/students/{id}");

        System.out.println(response.prettyPrint());

        assertThat(response.getStatusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getMap("$").size())
                .isPositive()
                .as("Validating the size of an array response.")
                .isGreaterThan(0);

        assertThat(response.jsonPath().getString("last_name"))
                .isEqualTo(lastName)
                .as("Comparing LastName Cnode in the response")
                .hasSizeBetween(3,30);

    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {2,"Hara"},
                {3,"Doe"},
                {4,"Do"},
        };
    }
}
