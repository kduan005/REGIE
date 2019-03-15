I. Classes modeling actors:
User Interface:
User Interface includes two operations:
1. login
2. search courses
These two operations are shared by students, faculties and administrators

Student class:
Student class extends User Interface and has four additional methods:
1. registerCourse
2. dropCourse
3. printCoursesRegistered
4. viewGrades

Faculty class:
Faculty class extends User Interface and has two additional methods:
1. assignGrade
2. changeGrade

II. DB connection:
There currently is a DB connector that creates a singleton of connection object,
and every data transaction will use the same connection to talk to the database.

III. User Interfaces
There currently are three GUI form built using Swing:
1. Login
The entrance of the whole program. When launching the program, a new Login instance
will be instantiated. A User object will be constructed within Login's constructor.
Both Login object and User object are singleton since there can be only one user
logged in at one time. The User object will be passed along across different
frontend form objects with login information coming through.

2. StudentHome
Depends on what type of user object is created at runtime, if the user is a student,
StudentHome will be popped up.
It contains four functionalities:
Search Course, Show My Course, View My Grade and Register and Drop Course.

3. SearchCourse
Every user should be able to query about detail information about a specific
course once he gets logged in the system. The form will be present after user click
on Search Course button on the homepage.

IV. To be built:
1. Complete all functionalities on StudentHome. Currently Show My Course functions
well.

2. Build FacultyHome as for the homepage for faculties after login. It should contain
functionalities including Search Course, Assign Grade (assign grade to a specific student)
and Change Grade(change grade for a specific student).

3. It's yet to be further clarified how Student and Faculty objects should interact
with different GUI form objects.

4. Also SqlSyntax class might not be necessary and might be refactored.
