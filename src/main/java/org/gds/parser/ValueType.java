package org.gds.parser;

public enum ValueType {

    Integer("Integer"),

    String("String"),

    Long("Long"),

    Float("float"),

    Double("double"),

    Char("char"),

    JSON("json"),

    Object("object"),

    Array("array"),

    Short("short");

    String value;

    ValueType(String s){
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
