## Summary
This project is a basic quiz app for android which also has user registration and score tracking .\
I made this project to get some hands on experience with Java for android and gain familiarity with the Android Studio IDE.\
To see how it works I uploaded a short video [here]().\
If you wish to run this app yourself, download this repo and run the code using the Android Studio android emulator.

## Technical details
To develop this app I used the Java with the Android Studio IDE.\
To store all the user data, quiz questions, and quiz results I used SQLite.\
The user login and registration feature works but does not have any security features as it is simply meant to demonstrate storing and retrieving data from the DB.

## Challenges 
To be able access the same database across all of the classes I initially tried to pass the DB object through intent, but this doesn't work. I realised I should instead use a singleton pattern for the instantiation of DBHelper, which enables all the classes to access the DB while ensuring only one DB instance exists. 

If you have any questions contact me here [msam.dev/contact](https://msam.dev/contact).
