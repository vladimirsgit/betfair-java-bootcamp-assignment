package org.admin;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        String table = "users";
        String[] columns = {"username", "email"};
        String[][] conditions = {{"username", "email"}};
        String[] conditionsValues = {"myusername", "my@email.com"};

        SelectQuery selectQuery = new SelectQuery.SelectQueryBuilder("users")
                .withColumns(columns).withConditions(conditions).withConditionsValues(conditionsValues).build();

        AccessDB.getInstance().select((SelectQuery) selectQuery);





    }


}