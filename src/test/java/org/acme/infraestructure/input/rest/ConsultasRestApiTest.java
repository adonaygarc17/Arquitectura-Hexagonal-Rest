package org.acme.infraestructure.input.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@QuarkusTest
class ConsultasRestApiTest {
  /*  @Test
    void shouldGetFacturaWhenSendPost() {
        given().when().post("/factura?skip=1&limit=1")
                .then().statusCode(200);
    }*/

    @Test
    void shouldGetErrorFacturaWhenSentLimitOver100() {
        given().when().post("/factura?skip=1&limit=112")
                .then().statusCode(400)
                .assertThat().body("id",equalTo("400 bad request"));
    }

    @Test
    void shouldGetErrorFacturaWhenSentSkipOver100() {
        given().when().post("/factura?skip=123&limit=12")
                .then().statusCode(400)
                .assertThat().body("id",equalTo("400 bad request"));
    }

    @Test
    void shouldGetErrorFacturaWhenSentBadPath() {
        given().when()
                .post("/faura?skip=123&limit=12")
                .then()
                .statusCode(404)
                .assertThat().body("id", equalTo("404 bad request"));
    }


}