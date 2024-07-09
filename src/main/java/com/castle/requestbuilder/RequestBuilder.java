package com.castle.requestbuilder;

import com.castle.enums.PropertyType;
import com.castle.utils.PropertyUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RequestBuilder {

    private RequestBuilder(){}

    public static RequestSpecification buildRequestForGetCalls(){
        return given()
                .baseUri(PropertyUtils.getValue(PropertyType.BASEURL))
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForPostCalls(){
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }

}
