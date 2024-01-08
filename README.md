# Personal Project - Valorant Match Tracker

## For the professional gameplay enthusiasts!

Do you also love to play and watch professional Valorant? Do you also wish to be able to track the stats of any number 
of **matches**? Well, that is exactly what my Personal Project is made for!

My application will have customizable features such as:

- creating a match with names of two teams
- setting the map of the match
- setting the score of each team
- view all maps played throughout all matches
- view a list of all matches
- be able to edit any selected match
- be able to remove any selected match

As a Valorant competitive tournament watcher myself, I really wanted to build a customizable
game tracker to keep track of my match and stats predictions. 
## User Stories
- As a user, I want to be able to create a new match.
- As a user, I want to be able to set a match with two teams and input their scores.
- As a user, I want to create an arbitrary number of matches and input the 2 participating teams, 
map, and score for each.
- As a user, I want to view a list of matches played,
selecting a match will display the 2 participating teams, map, and score.
- As a user, I want to select a match and edit all inputs.
- As a user, I want to be able to see a list of maps played throughout all matches.
- As a user, I want to have the option to load a tournament from file or start new.
- As a user, I want to have the option to save the tournament before quitting the application.

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking
the "add match" button, and inputting the correct information given each pop-up prompt. This will add a match to the 
match list, and is displayed underneath the buttons.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking
the "get maps" button. A pop-up will appear with all the maps played from each match.
- You can locate my visual component by running the GUI.main. You can see a picture of a Valorant
spray right on the landing page (as a label). 
- You can save the state of my application by clicking the "save" button.
- You can reload the state of my application by clicking the "load" button

## Phase 4: Task 2

- Application closed.
- Logged events:
- Team 1 added
- Team 2 added
- Team 1 score added
- Team 2 score added
- Map added
- Match added
- List of Maps played were displayed.

## Phase 4: Task 3
If I had more time to work on my application, the refractoring I would do
is to implement the Singleton Design Pattern. I specifically chose this design pattern 
because my future plans for this application is to add the ability
for teams to become eliminated if they lose a game against another team.
To ensure that the losing team get removed from the list of teams still
contending in the tournament, I would have to use a design where there can
only be one instance of the list of teams, and because this list will be accessed by
multiple classes, having the Singleton Design Pattern will allow global access
to the instance from anywhere in the program and that each object will have access
to just a single instance.