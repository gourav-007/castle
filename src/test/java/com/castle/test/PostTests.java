package com.castle.test;
import com.castle.constants.FCWithSingleton;
import com.castle.constants.FrameworkConstants;
import com.castle.pojo.Employee;
import com.castle.requestbuilder.RequestBuilder;
import com.castle.utils.ApiUtils;
import com.castle.utils.RandomUtils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.castle.requestbuilder.RequestBuilder.buildRequestForPostCalls;
import static io.restassured.RestAssured.given;

public class PostTests {

    @Test
    public void postCallTest(){
        //Create POJO of Employee using Lombok Builder
        Employee employee = Employee
                .builder()
                .setId(RandomUtils.getId())
                .setLname(RandomUtils.getLastName())
                .setFname(RandomUtils.getFirstName())
                .build();

        Response response = buildRequestForPostCalls()
                .log()
                .all()
                .body(employee)
                .post("/students");

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);
    }

    @Test
    public void postRequestUsingExternalFile(Method method){
        String resource = ApiUtils
                .readJsonAndGetAsString(FCWithSingleton.getInstance().getRequestJsonFolderPath()+"postRequestUsingExternalFile.json")
                .replace("Hello","Gourav")
                .replace("World","Jain")
                .replace("number",String.valueOf(RandomUtils.getId()));

        Response response = buildRequestForPostCalls()
                .body(resource)
                .post("/students");

        ApiUtils.storeJsonAsString(FCWithSingleton.getInstance().getResponseJsonFolderPath()+method.getName()+".json", response);

        System.out.println(response.prettyPrint());

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);

    }
}
