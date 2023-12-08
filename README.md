# betfair-java-bootcamp-assignment

**AccessDB Class**

`AccessDB` is a singleton used for managing database connections and methods.

**SelectQuery Class**

`SelectQuery` utilizes an inner static class, `SelectQueryBuilder`, to build select queries. You can dynamically add multiple conditions.

**QueryHelper Class**

`QueryHelper` provides static methods for preparing statements with specified parameters.

**Parameters:**
- `Table`
- `Columns[]`: Array of strings representing the desired columns.
- `Conditions[][]`: 2D array representing conditions. Elements within inner arrays are connected with the AND operator, while inner arrays are connected with the OR operator.
- `ConditionsValues[]`: Array of strings representing values for the conditions, in order.

**Example:**
```java
String table = "users";
String[] columns = {"username", "email"};
String[][] conditions = {{"username", "email"}, {"firstname}};
String[] conditionsValues = {"myusername", "my@email.com", "myfirstname"};
```
Final query will look like this: 
SELECT username, email FROM users WHERE (username = "myusername" AND email = "my@email.com") OR firstname = "myfirstname".
