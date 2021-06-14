package calc;

import calc.*;
import calc.util.*;

public class Sheet {
    private Evaluable[][] table;
    
    public Sheet(int numOfRows, int numOfCols) throws IllegalArgumentException {
        if(numOfCols > 26 || numOfCols < 0 || numOfRows < 0) {
            throw new IllegalArgumentException();
        }
        table = new Evaluable[numOfRows][numOfCols];
    }

    public void insertToSheet(String cn, Evaluable ev) throws SheetException {
        CellName c = new CellName();
        table[c.getRowIndexFromCellName(cn)][c.getColIndexFromCellName(cn)] = ev;
    }

    public Evaluable getFromSheet(String cn) throws SheetException {
        CellName c = new CellName();
        return table[c.getRowIndexFromCellName(cn)][c.getColIndexFromCellName(cn)];
    }

    public static int constructIntFromOperandStr(String operandStr, Sheet sheet) throws SheetException {
        if(Character.isLetter(operandStr.charAt(0))) {
            return sheet.getFromSheet(operandStr).eval(sheet);
        } else {
            return Integer.parseInt(operandStr);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[0].length; j++) {
                try{
                    sb.append(table[i][j].eval(this));
                } catch(Exception e) {}
                if(j < table[0].length - 1) {
                    sb.append(" ");
                }
            }
            if(i < table.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}