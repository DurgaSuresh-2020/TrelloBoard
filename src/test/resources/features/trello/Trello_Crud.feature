Feature: CRUD operations on Trello board

  Scenario Outline:Create a Board <BoardName> --> retrieve Board --> Update Board Name to <newBoardName> --> Retrive the lists on Board --> Delete Board
    Given I can Create a Trello Board "<BoardName>"
    And I can Request the created Trello Board
    When I can Update Trello board name to "<newBoardName>"
    And I can get the lists on the board
    Then I can Delete the Trello board
    Examples:
      | BoardName |newBoardName  |
      |Board_Board|Board_newBoard|

  Scenario Outline: Create a List <ListName> on <BoardName> board --> Retrieve List --> Update List Name to <newListName>--> Archive List
    Given I can Create a Trello List "<ListName>" on the Board "<BoardName>"
    And I can Request the created Trello List
    When I can Update Trello list name to "<newListName>"
    Then I can Archive the list
    And I tidy up the list trello board
    Examples:
      | ListName    | BoardName   | newListName  |
      | List_List   | List_Board  | List_newList |


  Scenario Outline:Create a Card --> Retrive Card --> Update Card Name --> Delete Card
    Given I can Create a card "<CardName>" on Trello List "<ListName>" on the Board "<BoardName>"
    And I can Request the created Trello Card
    And I can fetch the related board
    When I can Update Trello Card name to "<newCardName>"
    Then I can Delete the Trello Card
    And I tidy up the card trello board
    Examples:
      | CardName    | BoardName    | ListName  | newCardName  |
      | Card_Card   | Card_Board   | Card_List | Card_newCard |








