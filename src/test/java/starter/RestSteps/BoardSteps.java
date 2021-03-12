package starter.RestSteps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import starter.TrelloEndpoints;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardSteps {

    TrelloEndpoints endpoint;
    Response response;
    String apiURI = endpoint.BOARD+ "/{id}/";
    public String boardId;

    @Step("Create new Board")
    public String createBoard(String boardName) {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .queryParam("name", boardName)
                .post(TrelloEndpoints.BOARD);
        getStatus();
        validateResponseBody();
        boardId=response.body().jsonPath().get("id").toString();
        return boardId;
    }

    @Step("Request Board")
    public void getBoard() {
         response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",boardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .get(apiURI);
        getStatus();
        validateResponseBody();
    }


    @Step("Retrive Lists on the Board")
    public void getlist() {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",boardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .get(apiURI+"lists/");
        getStatus();
        validateResponseBody();
    }

    @Step("Update Board with new board name")
    public void updateBoardName(String newName) {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",boardId)
                .queryParam("name",newName)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .put(apiURI);
        getStatus();
        validateResponseBody(newName);
    }

    @Step("Delete Board")
    public void deleteBoard() {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",boardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .delete(apiURI);
        getStatus();
        validateResponseBody();
    }

    @Step("Verify response status")
    public void getStatus()
    {
        SerenityRest
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Verify if response body is not empty")
    public void validateResponseBody()
    {
        assertThat(SerenityRest.then().extract().asString()).isNotEmpty();
    }

    @Step("Verify if response body has {string}")
    public void validateResponseBody(String name)
    {
        assertThat(SerenityRest.then().extract().asString()).contains(name);
    }

    public void deleteBoard(String boardId)
    {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",boardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .delete(apiURI);
        getStatus();
        validateResponseBody();
    }

}
