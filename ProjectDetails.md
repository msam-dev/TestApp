## Summary
This project is an android quiz app I made that lets a user test their knowledge on a variety of topics by answering questions. It also has user registration so you can login and keep track of the quiz scores for a specific user.\
To see how it works I uploaded a short video [here]().\
If you wish to test this app yourself, simply download it into Android Studio and use the android emulator.

## Technical details
For development of the app, I used the java inside of Android Studio IDE.\
To store all the user data, quiz question and quiz results I used SQLiteDatabase.\
The user login and registration feature works but does not have security as it is simply meant to demonstrate storing and retrieving data in the database.

## Challenges 
To be able access the same database across all of the classes normally I would have thought to pass the DB object through intent, but it does not work so I decided to use a singleton pattern for the instantiation of DBHelper. This is also used to ensure only one instance of the DB exists at once.

If you have any questions contact me here [msam.dev/contact](https://msam.dev/contact).
