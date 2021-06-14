package calc;

import calc.util.SheetException;

public class Num implements Evaluable {
    private int n;

    public Num(int n) throws IllegalArgumentException {
        if(n < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
    }

    public int eval(Sheet sheet) {
        return n;
    }
}