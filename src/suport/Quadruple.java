/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suport;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class Quadruple implements Comparable<Quadruple>{
    private int index;
    private String operator;
    private String argument1;
    private String argument2;
    private String result;

    public Quadruple(int index, String operator, String argument1, String argument2, String result) {
        this.index = index;
        this.operator = operator;
        this.argument1 = argument1;
        this.argument2 = argument2;
        this.result = result;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getArgument1() {
        return argument1;
    }

    public void setArgument1(String argument1) {
        this.argument1 = argument1;
    }

    public String getArgument2() {
        return argument2;
    }

    public void setArgument2(String argument2) {
        this.argument2 = argument2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }

    @Override
    public int compareTo(Quadruple o) {
        if (this.index < o.getIndex()){
            return -1;
        } else if (this.index > o.getIndex()){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.index + ": [" + this.operator + " " + this.argument1 + " " + this.argument2 + " " + this.result + "]";
    }
}
