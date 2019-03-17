--This repo contains final deliverables for OOP course project -- design a university
course registration system incorporating design patterns covered on class. The
program was coded in Java.

--Files in the repo include:
I. 'readme.txt' helping to navigate the program;
II. 'REGIE' folder, within which there are a copy of database 'REGIE.sql' and source
    code in the sub-directory 'REGIE' for the program to run.
    1. About the database:
      You could easily import the database into your local computer using
      Mysql Workbench.
      To download Mysql Workbench, visit
      https://dev.mysql.com/downloads/workbench/
      To import database into your local computer, visit
      https://www.youtube.com/watch?v=Jvul-wr-_Bg
      for instructions.
    2. To run the program
      Import the package into an IDE say IntelliJ IDEA. The main function resides
      in 'Login.java'. Compile and run the program, if successfully connected to
      the database, you'll be able to see a login form popped up.
      Database connection configurations are in 'DBConnection.java'. Default User
      Name and Password for connection are 'root' and 'pass1234'. Change according
      to your local settings.
III. Screenshots of UI and usage demo

--Classes and methods in source code
I. GUIs:
1. Login
Generate a login frame, allowing user to login with user name and password
2. StudentHome
Homepage for student users. If user type is Student, user will be directed to StudentHome,
where the student can:
    1)Choose to show courses that he currently is enrolled in
    2)View the grades for these courses
    3)Search a specific course by name
    4)Register/drop a specific course by name
    5)Safely logout
3. StudentMain
Student will be redirected to StudentMain when they click on Search Course or
Register/Drop Course on StudentHome. The page will further allow student to input
course name
4. FacultyHome
Homepage for faculty users. If user type is Faculty, user will be directed to
FacultyHome, where the faculty can:
    1)Search Course
    2)Search students' grade of those who enrolled in the class the faculty teaches
    2)Assign or Change Grade for students from the course he teaches
5. FacultyMain
Main page that allows faculty users to Search Course, search grades, and
Assign/Change Grades with input of course name, student name and intended grade.

II.Classes modeling actors
User class:
Has a search course method which will be shared among all type of users including
Student, Faculty and Administrator.

Student class:
Student class extends User class and has four additional methods:
1. registerCourse
2. dropCourse
3. showMyCourse
4. viewGrades

Faculty class:
Faculty class extends User class and has two additional methods:
1. showGrade
2. assignGrade (shared method between assign and change grades)

Administrator:
Currently Administrator user could only search course(however no GUI form built for
Administrator user yet).

III. DB connection:
There currently is a DB connector that creates a singleton of connection object,
and every data transaction will use the same connection to talk to the database.

IV. Test files:
There are test files testing on methods implemented in the following four classes:
1) User
2) Student
3) Faculty
4) Administrator
All tests passed.

--Sample testing accounts:
I. Student:
1) Username:
    james@uchicago.edu
Password:
    james1234
Courses registered and grades:
    Intro to Programming  A
    Blockchain  B+
    Parallel Programming  A-
2) Username:
    jason@uchicago.edu
Password:
    jason1234
Courses registered and grades:
    Intro to Programming  A-

II. Faculty
1) Username:
    jane@uchicago.edu
Password:
    jane1234
Course taught:
    Intro to Programming
2) Username:
    mark@uchicago.edu
Password:
    mark1234
Course taught:
    Blockchain
3) Username:
    sam@uchicago.edu
Password:
    sam1234
Course taught:
    Parallel Programming
