import entityfactory.UserFactory;
import helpers.ErrorResponseHelper;
import io.restassured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.PropertyFactory.getBaseUriLinkProperty;
import static utils.PropertyFactory.getRegisterPath;

public class UserRegistrationTest {


    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = getBaseUriLinkProperty();
    }

    @Test (priority = 1)
    public void createNewUserBlockingTest() {

        Response response = given()
                .header("Content-Type","application/json")
                .body(UserFactory.createUser())
                .log()
                .all()
                .when()
                .post(getBaseUriLinkProperty() + getRegisterPath())
                .then()
                .log()
                .all()
                .statusCode(400)
                .extract()
                .response();

        ErrorResponseHelper errorResponse = response.as(ErrorResponseHelper.class);
        assertThat(errorResponse.getError()).isEqualTo("Note: Only defined users succeed registration");

   }
}
