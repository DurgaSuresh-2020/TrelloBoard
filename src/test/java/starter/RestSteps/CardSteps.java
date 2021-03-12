package starter.RestSteps;


import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import starter.TrelloEndpoints;

import static org.assertj.core.api.Assertions.assertThat;


//import static org.hamcrest.MatcherAssert.assertThat;


public class CardSteps {

    BoardSteps board = new BoardSteps();
    ListSteps list = new ListSteps();
    Response response;
    String boardId;
    String listId;
    String apiURI = TrelloEndpoints.CARD + "/{id}/";
    private String cardId;

    @Step("Create new Card")
    public String createCard(String cardName,String listName,String boardName) {
        listId = list.createList(listName,boardName);
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .queryParam("name", cardName)
                .queryParam("idList",listId)
                .post(TrelloEndpoints.CARD);
        getStatus();
        validateResponseBody();
        cardId=response.body().jsonPath().get("id").toString();
        return cardId;
    }


    @Step("Request Card")
    public void getCard() {
         response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",cardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .get(apiURI);
        getStatus();
        validateResponseBody();
    }

    @Step("Update Card with new Card name")
    public void updateCardName(String newName) {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",cardId)
                .queryParam("name",newName)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .put(apiURI);
        getStatus();
        validateResponseBody(newName);
    }

    @Step("Delete Board")
    public void deleteCard()
    {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",cardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .delete(apiURI);
        getStatus();
        validateResponseBody();
    }

    @Step("Get Board on which the card is linked")
    public String getBoardwithCard()
    {
        response = SerenityRest
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .pathParam("id",cardId)
                .queryParam("key", TrelloEndpoints.KEY)
                .queryParam("token", TrelloEndpoints.TOKEN)
                .get(apiURI+"board");
        getStatus();
        validateResponseBody();
        boardId=response.body().jsonPath().get("id").toString();
        return boardId;
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

    @Step("Delete board")
    public void deleteBoard()
    {
        board.deleteBoard(boardId);
    }
}
