package starter.CrudOperations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.RestSteps.ListSteps;

public class ListOperations {


        @Steps
        ListSteps list;

        @Given("I can Create a Trello List {string} on the Board {string}")
        public void createNewList(String ListName,String BoardName)
        {
                list.createList(ListName,BoardName);
        }

        @And("I can Request the created Trello List")
        public void requestList()
        {
                list.getList();
        }

        @When("I can Update Trello list name to {string}")
        public void updateListName(String arg1) {

                list.updateListName(arg1);
        }

        @Then("I can Archive the list")
        public void archiveList() {
                list.archiveList();
        }

        @And("I tidy up the list trello board")
        public void tidyUp()
        {
                list.deleteBoard();
        }

}


