package starter.RestSteps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import starter.TrelloEndpoints;

import static org.assertj.core.api.Assertions.assertThat;

public class    ListSteps {

    BoardSteps board = new BoardSteps();
    Response response;
    String boardId;
    String listId;
    String apiURI = TrelloEndpoints.LIST + "/{id}/";

    @Step("Create new List")
    public String createList(String listName,String boardName) {
        boardId = board.createBoard(boardName);
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .queryParam("name", listName)
                .queryParam("idBoard",boardId)
                .post(TrelloEndpoints.LIST);
        getStatus();
        validateResponseBody();
        listId=response.body().jsonPath().get("id").toString();
        return listId;
    }


    @Step("Request List")
    public void getList() {
         response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",listId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .get(apiURI);
        getStatus();
        validateResponseBody();
    }

    @Step("Update List with new List name")
    public void updateListName(String newName) {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",listId)
                .queryParam("name",newName)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .put(apiURI);
        getStatus();
        validateResponseBody(newName);
    }

    @Step("Archive List")
    public void archiveList()
    {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",listId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .queryParam("value","true")
                .put(apiURI+"closed/");
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

    public void deleteBoard() {
        board.deleteBoard(boardId);
    }

}
