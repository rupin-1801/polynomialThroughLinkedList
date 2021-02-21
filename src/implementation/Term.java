package implementation;

public class Term {
    int coefficient;
    int power;
    Term nextTerm;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
        this.nextTerm = null;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public Term getNextTerm() {
        return nextTerm;
    }

    public void setNextTerm(Term nextTerm) {
        this.nextTerm = nextTerm;
    }
}
