package implementation;

import myinterface.PolynomialADT;

public class Polynomial implements PolynomialADT {

    // head points to first term of polynomial i.e. term having highest degree.
    Term head = null;

    // points to the lowest degree term.
    Term tail = null;

    // to store the degree of the polynomial equation.
    int degree = 0;

    // returns the head term of the linked list.
    public Term getHead() {
        return head;
    }

    // converts the given expression into linked list of type polynomial.
    @Override
    public void stringToLinkedList(String equation) {
        // variables to store coefficient and power of each term.
        int coefficient, power;

        // to store the starting index of the current term.
        int previousIndex = 0;

        // to store the current term to extract coefficient and
        // power of the term from equation.
        String term;

        // traverse in the equation
        for (int i = 1; i < equation.length(); i++) {

            // check whether the current index is an operator or not or the end of expression
            // (to check the end of current term)
            if (isOperator(equation.charAt(i)) || (i == equation.length() - 1)) {

                // stores the current term in the variable term.
                // error : last character of equation will not be in 'term'. sol : line 46.
                term = equation.substring(previousIndex, i);

                // in case of last term adds the last character of equation to the term.
                if (i == equation.length() - 1) {
                    term = term + equation.charAt(equation.length() - 1);
                }

                // extract coefficient from term.
                String c = term.substring(0, term.indexOf('x'));

                // special case : no coefficient - add '1' as a coefficient.
                if (c.equals("") || c.equals("+") || c.equals("-")) {
                    c = c + "1";
                }

                // cast coefficient to int
                coefficient = Integer.parseInt(c);

                // extract power from term.
                power = Integer.parseInt(term.substring(term.indexOf('x') + 2));

                // add the extracted term to the end of linked list.
                addTerm(coefficient, power);

                // store i in previous index as the starting index for the next term.
                previousIndex = i;
            }
        }
    }

    // check whether the given character is an operator or not
    // only '+' and '-' are valid operator for this case
    @Override
    public boolean isOperator(char c) {
        return (c == '+' || c == '-');
    }

    @Override
    public void addPolynomial(Polynomial polynomial1, Polynomial polynomial2) {

        // reference variable of type term to traverse in each polynomial.
        Term currentTerm1, currentTerm2;

        // stores polynomial of higher degree in first reference variable and
        // lower degree in second reference variable.
        if (polynomial1.degree() > polynomial2.degree()) {
            currentTerm1 = polynomial1.getHead();
            currentTerm2 = polynomial2.getHead();
        } else {
            currentTerm1 = polynomial2.getHead();
            currentTerm2 = polynomial1.getHead();
        }

        // check whether any of the reference variable is at end of linked list.
        // special case when both polynomial have different lowest power.
        while (currentTerm1 != null && currentTerm2 != null) {

            // simply add coefficient of both terms if their powers matches.
            if (currentTerm1.getPower() == currentTerm2.getPower()) {
                int coefficient = currentTerm1.getCoefficient() + currentTerm2.getCoefficient();

                // add resultant term to the end of resultant polynomial.
                addTerm(coefficient, currentTerm2.getPower());

                // point both reference variables to their next term.
                currentTerm1 = currentTerm1.getNextTerm();
                currentTerm2 = currentTerm2.getNextTerm();
            }

            // when currentTerm1 has higher than currentTerm2
            // e.g. currentTerm1 = x^2 , currentTerm2 = 3x^1
            // add x^2 to the resultant polynomial.
            else if (currentTerm1.getPower() > currentTerm2.getPower()) {
                addTerm(currentTerm1.getCoefficient(), currentTerm1.getPower());
                currentTerm1 = currentTerm1.getNextTerm();
            }

            // when currentTerm2 has higher than currentTerm1
            // e.g. currentTerm1 = 4x^2 , currentTerm2 = 2x^3
            // add x^2 to the resultant polynomial.
            else {
                addTerm(currentTerm2.getCoefficient(), currentTerm2.getPower());
                currentTerm2 = currentTerm2.getNextTerm();
            }
        }

        // when first polynomial ends earlier i.e. have greater lowest power.
        // e.g. polynomial1 = x^3 + x^2  , polynomial2 = x^2 + 2x + 1
        // remaining terms are 2x and 1 in polynomial2
        Term remainingTerm;
        if (currentTerm1 != null) {
            remainingTerm = currentTerm1;
        } else {
            remainingTerm = currentTerm2;
        }

        // traverse in polynomial to add remaining terms.
        while (remainingTerm != null) {
            addTerm(remainingTerm.getCoefficient(), remainingTerm.getPower());
            remainingTerm = remainingTerm.getNextTerm();
        }
    }

    // check whether the linked list is empty or not.
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // add term to the end of linked list.
    @Override
    public void addTerm(int coefficient, int power) {
        Term term = new Term(coefficient, power);

        // special case : if list is empty.
        if (isEmpty()) {
            head = term;
            tail = term;
        } else {
            tail.setNextTerm(term);
            tail = term;
            degree++;
        }
    }

    // returns the degree of the given polynomial
    @Override
    public int degree() {
        return degree;
    }

    // print the given polynomial
    @Override
    public void display() {

        // temporary variable to traverse in the list.
        Term temp = head;
        System.out.println("Result is: ");
        while (temp != null) {
            // not print the terms having 0 coefficient.
            if (temp.getCoefficient() != 0) {
                displayTouchUp(temp);
            }
            temp = temp.getNextTerm();
        }
    }

    // method to format the output string
    @Override
    public void displayTouchUp(Term temp) {
        String c = String.valueOf(temp.getCoefficient());
        int coefficient = temp.getCoefficient();
        String result = "";
        if (coefficient < 0) {
            result = result + c.substring(1);
        } else if (coefficient > 1) {
            result = c;
        }

        if (coefficient != 0) {
            if (temp.getPower() > 1) {
                result = result + "x^" + temp.getPower();
            } else if (temp.getPower() == 1) {
                result = result + "x";
            }
        }

        System.out.print(result + " ");

        if (temp.getNextTerm() != null) {
            if (temp.getNextTerm().getCoefficient() < 0) {
                System.out.print("- ");
            } else {
                System.out.print("+ ");
            }
        }
    }
}
