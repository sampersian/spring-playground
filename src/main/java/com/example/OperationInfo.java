package com.example;

public class OperationInfo {
    private String operation;
    private Integer x;
    private Integer y;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setX(String x) {
        this.x = Integer.parseInt(x);
    }

    public void setY(String y) {
        this.y = Integer.parseInt(y);
    }


    public String buildString() {
        if (operation == null) {
            this.operation = "add";
        }

        if (x != null && y != null) {
            if (operation.equals("subtract")) {
                return x+" - "+y+" = "+Math.subtractExact(x, y);
            } else if (operation.equals("multiply")) {
                return x+" * "+y+" = "+Math.multiplyExact(x, y);
            } else if (operation.equals("divide")) {
                return x+" / "+y+" = "+(x / y);
            } else {
                return x+" + "+y+" = "+Math.addExact(x, y);
            }
        } else {
            return "invalid parameters";
        }

    }
}
