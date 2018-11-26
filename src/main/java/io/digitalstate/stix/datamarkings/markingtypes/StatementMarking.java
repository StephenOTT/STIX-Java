package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({"statement"})
public class StatementMarking extends MarkingObjectTypeCommonProperties implements MarkingObjectType {

    private static final String type = "statement";

    private String statement;


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
        Objects.requireNonNull(statement, "statement cannot be null");
        this.statement = statement;
    }
}
