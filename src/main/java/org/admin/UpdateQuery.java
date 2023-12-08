package org.admin;

public class UpdateQuery {
    private final String table;
    private final String[] columns;
    private final String[][] conditions;
    private final String[] conditionsValues;

    private UpdateQuery(String table, String[] columns, String[][] conditions, String[] conditionsValues) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
        this.conditionsValues = conditionsValues;
    }

    public String[] getConditionsValues() {
        return conditionsValues;
    }

    public String buildQuery(){
        String whereCondition = "";
        if(conditions != null){
            QueryHelper.addParameterMarkerForConditions(conditions);
            QueryHelper.addParameterMarkerForColumns(columns);
            whereCondition = QueryHelper.createWhereClause(conditions);
        }

        return "UPDATE " + table + " SET " + String.join(", ", columns) + whereCondition;
    }
    public static class UpdateQueryBuilder{
        private String table;
        private String[] columns;
        private String[][] conditions;
        private String[] conditionsValues;

        public UpdateQueryBuilder(String table) {
            this.table = table;
        }

        public UpdateQueryBuilder withColumns(String[] columns){
            this.columns = columns;
            return this;
        }
        public UpdateQueryBuilder withConditions(String[][] conditions){
            this.conditions = conditions;
            return this;
        }
        public UpdateQueryBuilder withConditionsValues(String[] conditionsValues){
            this.conditionsValues = conditionsValues;
            return this;
        }

        public UpdateQuery build(){
            return new UpdateQuery(table, columns, conditions, conditionsValues);
        }
    }

}
