package myinterface;

import implementation.Polynomial;
import implementation.Term;

public interface PolynomialADT {
    void addPolynomial(Polynomial polynomial1, Polynomial polynomial2);

    void addTerm(int coefficient, int power);

    void stringToLinkedList(String equation);

    void display();

    void displayTouchUp(Term temp);

    boolean isOperator(char c);

    boolean isEmpty();

    int degree();
}
