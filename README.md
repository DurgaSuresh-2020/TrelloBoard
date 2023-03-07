 
# Framework:

•	Serenity BDD framework has been leveraged

•	RestAssured, cucumber libraries has been used

•	Test has been created using Java

•	Reporting based on Serenity report
	
•	Intellij IDE was used
 


# Framework Architecture:

•	Test -> resources -> features.Trello has the Feature file

•	Test -> Java -> Serenity ->CrudOperations is the glue code folder with step definitions

•	Test -> Java -> starter ->RestSteps has the rest assured steps

•	Test -> Java -> starter ->CucumberTestSuite has the runner which should be executed.


# Steps to initiate the test run :

•	Download the project
 
•	Import as Maven project in your IDE

•	In the terminal window run “mvn clean verify” command

•	Deatiled report will be generated in the target\site\serenity as index.html
