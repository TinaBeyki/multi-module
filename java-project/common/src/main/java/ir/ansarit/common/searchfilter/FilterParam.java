package ir.ansarit.common.searchfilter;

import lombok.Builder;

@Builder
public class FilterParam {

    private String key;
    private String value;
    private OperatorEnum operatorEnum;

    public FilterParam() {
    }

    public FilterParam(String key, String value, OperatorEnum operatorEnum) {
        this.key = key;
        this.value = value;
        this.operatorEnum = operatorEnum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
    }
}
