import entityenum.ColleagueEntity;
import entityfactory.ColleagueFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static utils.PropertyFactory.*;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ColleaguesTest {

    @Test (priority = 1)
    public void createNewColleagueTest() {
        // POST
        Response response = given()
                .header("Content-Type", "application/json")
                .body(ColleagueFactory.createColleague())
                .log()
                .all()
                .when()
                .post(getBaseUriLinkProperty() + getColleaguePath())
                .then()
                .log()
                .all()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .extract()
                .response();

        JsonPath jsonPathEvaluator = response.jsonPath();

        String createdColleagueId = jsonPathEvaluator.getString("id");
        String createdColleagueName = jsonPathEvaluator.getString("name");
        String createdColleagueJob = jsonPathEvaluator.getString("job");
        String createdColleagueCreatedAt = jsonPathEvaluator.getString("createdAt");

        assertThat(createdColleagueId)
                .isNotNull()
                .isNotEmpty();

        assertThat(createdColleagueName).isEqualTo(ColleagueEntity.NAME.getFieldName());
        assertThat(createdColleagueJob).isEqualTo(ColleagueEntity.JOB.getFieldName());

        assertThat(createdColleagueCreatedAt)
                .isNotNull()
                .isNotEmpty();
    }

    @Test (priority = 2)
    public void getNotExistingColleagueTest(){
        // GET SINGLE NOT EXISTING USER (щойно створеного юзера перевірити неможливо, тому що ID присовюється якось хаотично
        // і деколи присвоює те, яке вже існує в системі (2), що викликає падіння тестів)

        given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get(getBaseUriLinkProperty() + "/users/512545236")
                .then()
                .log()
                .all()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");
    }

    @Test (priority = 3)
    public void getExistingColleagueTest(){
        // GET SINGLE EXISTING USER (ID = 2)
        Response getUserResponse = given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get(getBaseUriLinkProperty() + "/users/2")
                .then()
                .log()
                .all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .extract()
                .response();

        JsonPath getUserJsonPathEvaluator = getUserResponse.jsonPath();

        String gotColleagueEmail = getUserJsonPathEvaluator.getString("data.email");
        String gotColleagueFirstName = getUserJsonPathEvaluator.getString("data.first_name");
        String gotColleagueLastName = getUserJsonPathEvaluator.getString("data.last_name");
        String gotColleagueAvatar = getUserJsonPathEvaluator.getString("data.avatar");

        assertThat(gotColleagueEmail).isEqualTo("janet.weaver@reqres.in");
        assertThat(gotColleagueFirstName).isEqualTo("Janet");
        assertThat(gotColleagueLastName).isEqualTo("Weaver");
        assertThat(gotColleagueAvatar).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
    }

    @Test (priority = 4)
    public void updateExistingColleagueTest() {
        // PUT
        String requestBody = "{\n" +
                "  \"name\": \"Witch\",\n" +
                "  \"job\": \"the most important manager among all managers in the world\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log()
                .all()
                .when()
                .put(getBaseUriLinkProperty() + getColleaguePath() + "/3")
                .then()
                .log()
                .all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .extract()
                .response();

        JsonPath jsonPathEvaluator = response.jsonPath();

        String updatedColleagueName = jsonPathEvaluator.getString("name");
        String updatedColleagueJob = jsonPathEvaluator.getString("job");
        String updatedColleagueUpdatedAt = jsonPathEvaluator.getString("updatedAt");

        assertThat(updatedColleagueName).isEqualTo("Witch");
        assertThat(updatedColleagueJob).isEqualTo("the most important manager among all managers in the world");

        assertThat(updatedColleagueUpdatedAt)
                .isNotNull()
                .isNotEmpty();
    }

    @Test (priority = 5)
    public void deleteColleagueTest(){

        given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .delete(getBaseUriLinkProperty() + getColleaguePath() + "/3")
                .then()
                .log()
                .all()
                .statusCode(204)
                .statusLine("HTTP/1.1 204 No Content");
    }

}
