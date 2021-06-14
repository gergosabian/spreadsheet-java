package calc.util;

import calc.util.SheetException;

public class CellName {
    private static final String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isCellNameValid(String cellName){
        for(int i = 0; i < cellName.length(); i++) {
            if(cellName.charAt(i) == ' ') {
                return false;
            }
        }
        if(cellName.length() == 2) {
            if(!colIndexes.contains(String.valueOf(cellName.charAt(0))) ||
            Integer.parseInt(String.valueOf(cellName.charAt(1))) < 0) {
                return false;
            }
        } else if (cellName.length() < 2) {
            return false;
        } else {
            if(!colIndexes.contains(String.valueOf(cellName.charAt(0))) ||
            Double.parseDouble(cellName.substring(1)) != Math.floor(Double.parseDouble(cellName.substring(1)))) {
                return false;
            }
        }
        return true;
    }

    public int getRowIndexFromCellName(String cellName) throws SheetException {
        if(isCellNameValid(cellName)) {
            if(cellName.length() == 2) {
                return Integer.parseInt(String.valueOf(cellName.charAt(1)));
            } else {
                return Integer.parseInt(cellName.substring(1));
            }
        } else {
            throw new SheetException("invalid number in cellname");
        }
    }

    public int getColIndexFromCellName(String cellName) throws SheetException {
        if(isCellNameValid(cellName)) {
            return cellName.charAt(0) - 65;
        } else {
            throw new SheetException("invalid letter in cellname");
        }
    }
}