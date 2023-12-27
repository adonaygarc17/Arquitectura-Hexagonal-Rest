package org.acme.infraestructure.input.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RestApiTest {

    @Test
    void getDefinedProducts() {
        given().when().post("/factura?skip=1&limit=1")
                .then().statusCode(200);
    }
    @Test
    void getDefinedProductsErrorLimit() {
        given().when().post("/factura?skip=1&limit=112")
                .then().statusCode(400);
    }
    @Test
    void getDefinedProductsSkip() {
        given().when().post("/factura?skip=123&limit=12")
                .then().statusCode(400);
    }
}