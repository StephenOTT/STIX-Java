package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatementMarking implements MarkingObjectType {

    private static final String type = "statement";

    String statement;

    public StatementMarking(String statementValue){
        setStatement(statementValue);
    }

    //
    // Getters and Setters
    //

    @Override
    @JsonIgnore
    public String getType() {
        return type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
