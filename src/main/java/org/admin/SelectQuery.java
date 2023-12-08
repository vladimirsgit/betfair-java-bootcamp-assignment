package org.admin;

public class SelectQuery {
    private final String table;
    private final String[] columns;
    private final String[][] conditions;
    private final String[] conditionsValues;

    private SelectQuery(String table, String[] columns, String[][] conditions, String[] conditionsValues) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
        this.conditionsValues = conditionsValues;
    }

    public String[] getConditionsValues() {
        return conditionsValues;
    }

    public String[] getColumns() {
        return columns;
    }

    public String buildQuery(){
        String whereCondition = "";
        if(conditions != null){
            QueryHelper.addParameterMarkerForConditions(conditions);
            whereCondition = QueryHelper.createWhereClause(conditions);
        }
        return "SELECT " + String.join(", ", columns) + " FROM " + table + " " + whereCondition;
    }
    public static class SelectQueryBuilder{
        private String table;
        private String[] columns;
        private String[][] conditions;
        private String[] conditionsValues;

        public SelectQueryBuilder(String table) {
            this.table = table;
        }

        public SelectQueryBuilder withColumns(String[] columns){
            this.columns = columns;
            return this;
        }
        public SelectQueryBuilder withConditions(String[][] conditions){
            this.conditions = conditions;
            return this;
        }
        public SelectQueryBuilder withConditionsValues(String[] conditionsValues){
            this.conditionsValues = conditionsValues;
            return this;
        }

        public SelectQuery build(){
            return new SelectQuery(table, columns, conditions, conditionsValues);
        }
    }
}
