# LibraryManagerWithGUI

Welcome to my Library Manager! This is also my first in-depth GUI creation using Java Swing. This is meant to connect to SQL database(s) and give functionality to check out, return, find a current rental, search for a book, and search for status of an account. 

I also included screenshots of the tables to give an idea of how they look. 

The Database package is a one file package that is meant to give universal function to connecting to the database. Anytime there is a query and a need to establish a connection to the database, it uses the DBConnection class.

The DBEnterNames package is meant to read from the 3 .txt files included. AccountNames.txt, BookNames.txt, EmployeeNames.txt, are all meant to provide basic data in order to make the retrospective objects and then put the objects in the database table. These classes are only meant to be used once, in the initial initilization of the program. However, if later someone were to add or delete from the .txt files, the classes would be used again. It doesn't update the existing table, but rather deletes and inserts a new table. A better function would be to simply update the table if the file gets updated, but that is a feature for a later time. 

The Interface package is simply a package holding all the classes for the different screens included in the GUI. Each class is named properly according to the functionality being used within the screen. The login screen opens the main menu screen, and depending on the user option will open the appropriate screen by calling the class constructor for the appropriate class. 

The Manager package simply creates the Objects that are inserted into the databases. The user is a parent class to Account and Employee since both are similar, just have small differences in the functionallity of the class. This makes the code simplier as well. The testing class is simply the main which starts the program and launches the login screen.
