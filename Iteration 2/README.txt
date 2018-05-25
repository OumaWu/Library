Iteration 2 - Prototype

To run this version, you will have to include the JDBC library into your project.
So, include the .jar file "Iteration 2\Functional Demo\lib\mysql-connector-java-5.1.46" into your project.

You will also need to import our tables from the sql file "Iteration 2\Functional Demo\lib\library.sql".

Finally, we connect to the database using the root user, with the 111 password.
You can create this user, or change the parameters of line 19 of the file "LoginController.java" to correspond to yours :
DatabaseManager.configCRUDInstance(DBTool.MYSQL, "localhost", "library", "root", "111");

For the app now, there are 4 users authorized to login (you can add some in the administrators table). 
They're also listed in the library.sql:

Username	Password
axel		caliari
emmanuel	gay
victor		wang
jiahong		wu

Now that you're connected, you can display books, reservations and customers, add some, delete others...
