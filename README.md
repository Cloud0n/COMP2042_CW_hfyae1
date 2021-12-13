# Brick_Destroy

---------------------------REFACTORS-----------------------------------

###MVC Patter:
I have converted ball to MVC pattern, this class has its own conntroller and model view. 
The "Ball" class will be the model class which stores all data.
I then created a "ControllBall" class and moved all methods and data initialisation in "Ball" class to this class.
The "ControllBall" class will have all the getter and setter methods .

###Encapsulation:
I created most variables into getter and setter methods where needed to be able to access it directly.

###Package Organization:
I organised all the classes that i could seperate into their own packages that are related to their functions

###Extracting classes:
I have extracted all possible classes that wont make the code not run to make it cleaner and easier to edit and see. Most unused classes have also been deleted and the code has been edited to accomodate that.

---------------------------ADDITIONS-----------------------------------

###Instruction menu screen:
I added a new button that, when pressed, will load the class "InfoPage". In info page, the player is guided on how to play the game. There is also a button to return to the home menu after the user is done with reading instructions.

###Additional Bricks:
I have added additional bricks that have different properties, which are as follows
1) ConcreteBrick which has a higher strength than CementBrick by a value of 1, meaning it takes 3 hits to break
2) CarbonBrick which is the hardest brick and has the highest strength with a value of 69 as its strength

###Additional level:
I have added 5 new levels to the game as follows:
1) Level 5 which contains only cement with a total of 6 lines of brick
2) Level 6 which contains a mix of clay and concrete
3) Level 7 which contains a mix of concrete and cement
4) Level 8 which contains a mix of carbon and steel
5) Level 8 which contains only carbon with a total of 18 lines of brick

###Score and highscore:
I have added a scoring system that adds 1 point when a brick breaks, which is displayed in the screen when the game is played, while I have also added a highscore system that takes the scoring system and puts it into a file.