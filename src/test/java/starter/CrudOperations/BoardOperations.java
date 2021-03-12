package starter.CrudOperations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.RestSteps.BoardSteps;

public class BoardOperations {

        @Steps
        BoardSteps board;

        @Given("I can Create a Trello Board {string}")
        public void createNewBoard(String BoardName)
        {
         board.createBoard(BoardName);
        }

        @And("I can Request the created Trello Board")
        public void requestBoard()
        {
            board.getBoard();
        }

        @When("I can Update Trello board name to {string}")
        public void updateBoardName(String arg1) {

                board.updateBoardName(arg1);
        }

        @Then("I can Delete the Trello board")
        public void deleteBoard()
        {
                board.deleteBoard();
        }

        @And("I can get the lists on the board")
        public void getlist() {
                board.getlist();
        }
}


