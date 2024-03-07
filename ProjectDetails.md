## Summary
This project is an android quiz app I made that lets a user test their knowledge on a variety of topics by answering questions. It also has user registration so you can login and keep track of the quiz scores for a specific user.\
To see how it works I uploaded a short video [here]().\
If you wish to test this app yourself, simply download it into Android Studio and use the android emulator.

## Technical details
For development of the app, I used the java inside of Android Studio IDE.\
To store all the user data, quiz questions, and quiz results I used SQLiteDatabase.\
The user login and registration feature works but does not have any security as it is simply meant to demonstrate storing and retrieving data in the database.

## Challenges 
To be able access the same database across all of the classes I initially tried to pass the DB object through intent, but this doesn't not work. I realised I should instead use a singleton pattern for the instantiation of DBHelper, which enables all the classes to access the DB while ensuring only one DB instance exists. 

If you have any questions contact me here [msam.dev/contact](https://msam.dev/contact).
