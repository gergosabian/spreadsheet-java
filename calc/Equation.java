package calc;

import calc.util.*;
import calc.*;

public class Equation implements Evaluable {
    private String operand1;
    private String operand2;
    private char operator;
    
    public Equation(String equation) throws IllegalArgumentException {
        char c;
        boolean found = false;
        boolean invalid = false;
        for(int i = 0; i < equation.length(); i++){
            c = equation.charAt(i);
            if(c == '/' || c == '*' || c == '+' || c == '-') {
                operator = c;
                found = true;
            }
            if(c == '(' || c == ')') {
                invalid = true;
            }
        }
        if(!found || invalid) {
            throw new IllegalArgumentException();
        }

        CellName cn = new CellName();
        String tmp;
        String[] data;
        if(operator == '+') {
            data = equation.split("\\+");
        } else if(operator == '*') {
            data = equation.split("\\*");
        } else {
            data = equation.split(Character.toString(operator));
        }

        tmp = data[0];
        if(Character.isDigit(tmp.charAt(0)) && tmp.charAt(0) != '0' && tmp.length() == 1) {
            operand1 = tmp;
        } else if(Character.isDigit(tmp.charAt(0)) && tmp.charAt(0) != '0' && tmp.length() > 1) {
            int i = 1;
            while(i < tmp.length() && Character.isDigit(tmp.charAt(i))) {
                i++;
            }
            if(i == tmp.length()) {
                operand1 = tmp;
            } else {
                throw new IllegalArgumentException();
            }
        } else if(cn.isCellNameValid(tmp)) {
            operand1 = tmp;
        } else {
            throw new IllegalArgumentException();
        }

        tmp = data[1];
        if(Character.isDigit(tmp.charAt(0)) && tmp.charAt(0) != '0' && tmp.length() == 1) {
            operand2 = tmp;
        } else if(Character.isDigit(tmp.charAt(0)) && tmp.charAt(0) != '0' && tmp.length() > 1) {
            int i = 1;
            while(i < tmp.length() && Character.isDigit(tmp.charAt(i))) {
                i++;
            }
            if(i == tmp.length()) {
                operand2 = tmp;
            } else {
                throw new IllegalArgumentException();
            }
        } else if(cn.isCellNameValid(tmp)) {
            operand2 = tmp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int eval(Sheet sheet) throws ArithmeticException, SheetException {
        int a = Sheet.constructIntFromOperandStr(operand1, sheet);
        int b = Sheet.constructIntFromOperandStr(operand2, sheet);
        switch(operator) {
            case '+': return a + b;
            case '-': int c = a - b;
                      if(c < 0) { throw new ArithmeticException(); }
                      else { return c; }
            case '*': return a * b;
            case '/': if(b == 0) { throw new ArithmeticException(); }
                      else { return a / b; }
            default: break;
        }
        return 0;
    }
}