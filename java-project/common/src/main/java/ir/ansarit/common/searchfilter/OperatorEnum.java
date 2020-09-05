package ir.ansarit.common.searchfilter;

public enum OperatorEnum {
    equal("equal", "="),
    like("like", "like"),
    LessThan("lessThan", "<="),
    GreaterThan("greaterThan", ">="),
    notEqual("notEqual", "!="),
    IN("in", "15");

    private final String name;
    private final String operator;

    OperatorEnum(String name, String operator) {
        this.name =name;
        this.operator = operator;
    }

    public String toName() {
        return this.name;
    }

    public String toValue() {
        return this.operator;
    }


}
