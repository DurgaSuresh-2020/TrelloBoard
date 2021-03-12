package starter.CrudOperations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.RestSteps.CardSteps;

public class CardOperations {

        @Steps
        CardSteps card;

        @Given("I can Create a card {string} on Trello List {string} on the Board {string}")
        public void createCard(String cardName,String listName,String boardName)
        {
                card.createCard(cardName,listName,boardName);
        }

        @And("I can Request the created Trello Card")
        public void getCard()
        {
                card.getCard();
        }

        @When("I can Update Trello Card name to {string}")
        public void updateCard(String arg0) {
                card.updateCardName(arg0);
        }

        @Then("I can Delete the Trello Card")
        public void deleteCard()
        {
                card.deleteCard();
        }

        @And("I tidy up the card trello board")
        public void tidyUp()
        {
                card.deleteBoard();
        }

        @And("I can fetch the related board")
        public void FetchTheRelatedBoard() {
                card.getBoardwithCard();
        }
}


