package com.yash.design.redis;

public enum Type {
    SIMPLE_STRING("+"), //+OK\r\n
    SIMPLE_ERROR("-"), //-Error message\r\n
    INTEGER(":"),  //:1000\r\n
    BULK_STRING("$"), //$5\r\nhello\r\n
    ARRAY("*"),  //*5\r\n:1\r\n:2\r\n:3\r\n:4\r\n$5\r\nhello\r\n
    // All of the aggregate RESP types support nesting
    BOOLEAN("#"),
    DOUBLE(","),
    NULLS("_");

    private final String prefix;

    Type(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    // Example usage
    // public static void main(String[] args) {
    //     Type type = Type.SIMPLE_STRING;
    //     System.out.println(type.getPrefix());  
    // }
}
