package tests;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import calc.util.*;
import calc.*;

public class Tests {
    @Test
    public void TestIsCellNameValid() {
        CellName cn = new CellName();
        assertTrue(!cn.isCellNameValid(" 2"));
        assertTrue(!cn.isCellNameValid("c0"));
        assertTrue(!cn.isCellNameValid("D3.5"));
        assertTrue(cn.isCellNameValid("B5"));
        assertTrue(cn.isCellNameValid("C13"));
    }

    @Test
    public void TestGetRowIndexFromCellName() throws SheetException {
        CellName cn = new CellName();
        assertEquals(1, cn.getRowIndexFromCellName("D1"));
        assertEquals(22, cn.getRowIndexFromCellName("A22"));
    }

    @Test
    public void TestGetColIndexFromCellName() throws SheetException {
        CellName cn = new CellName();
        assertEquals(0, cn.getColIndexFromCellName("A2"));
        assertEquals(3, cn.getColIndexFromCellName("D8"));
    }

    @Test
    public void TestNum() {
        int x = 3;
        Num num = new Num(x);
        assertEquals(x, num.eval(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestSpaceEquation() {
        Equation eq = new Equation("A 2-C2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestSmallEquation() {
        Equation eq = new Equation("s3-g4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestBracketEquation() {
        Equation eq = new Equation("(A1-C1)");
    }

    @Test
    public void TestSheet() throws SheetException {
        Sheet sheet = new Sheet(3, 2);
        sheet.insertToSheet("A0", new Num(6));
        sheet.insertToSheet("A1", new Num(2));
        sheet.insertToSheet("A2", new Num(2));
        sheet.insertToSheet("B0", new Num(5));
        sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("B2", new Num(9));
        String expected = "6 5" + System.lineSeparator() + "2 6" + System.lineSeparator() + "2 9";
        assertEquals(expected, sheet.toString());
    }

    @Test
    public void TestSheetEquation() throws SheetException {
        Sheet sheet = new Sheet(3, 3);
        sheet.insertToSheet("A0", new Num(6));
        sheet.insertToSheet("A1", new Num(8));
        sheet.insertToSheet("A2", new Num(9));
        sheet.insertToSheet("B0", new Num(1));
        sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("B2", new Num(2));
        sheet.insertToSheet("C0", new Equation("A0+B0"));
        sheet.insertToSheet("C1", new Equation("A1+B1"));
        sheet.insertToSheet("C2", new Equation("A2+B2"));
        String expected = "6 1 7" + System.lineSeparator() + "8 6 14" + System.lineSeparator() + "9 2 11";
        assertEquals(expected, sheet.toString());
    }

    @Test
    public void TestSheetEquation2() throws SheetException {
        Sheet sheet = new Sheet(3, 4);
        sheet.insertToSheet("A0", new Num(6));
        sheet.insertToSheet("A1", new Num(8));
        sheet.insertToSheet("A2", new Num(9));
        sheet.insertToSheet("B0", new Num(1));
        sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("B2", new Num(2));
        sheet.insertToSheet("C0", new Equation("A0-B0"));
        sheet.insertToSheet("C1", new Equation("A1-B1"));
        sheet.insertToSheet("C2", new Equation("A2-B2"));
        sheet.insertToSheet("D0", new Equation("C0/2"));
        sheet.insertToSheet("D1", new Equation("C1/2"));
        sheet.insertToSheet("D2", new Equation("C2/2"));
        String expected = "6 1 5 2" + System.lineSeparator() + "8 6 2 1" + System.lineSeparator() + "9 2 7 3";
        assertEquals(expected, sheet.toString());
    }

    @Test
    public void TestSheetEquation3() throws SheetException {
        Sheet sheet = new Sheet(3, 3);
        sheet.insertToSheet("A0", new Num(6));
        sheet.insertToSheet("A1", new Num(8));
        sheet.insertToSheet("A2", new Num(9));
        sheet.insertToSheet("B0", new Num(1));
        sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("B2", new Num(2));
        sheet.insertToSheet("C0", new Equation("10*B0"));
        sheet.insertToSheet("C1", new Equation("10*B1"));
        sheet.insertToSheet("C2", new Equation("10*B2"));
        String expected = "6 1 10" + System.lineSeparator() + "8 6 60" + System.lineSeparator() + "9 2 20";
        assertEquals(expected, sheet.toString());
    }
}

