package main;

import implementation.Polynomial;

import java.util.Scanner;

// https://gist.github.com/rupin-1801/9302509e0cd5179156f1b4f623c9886c
public class MainClass {
    public static void main(String[] args) {

        // Object of scanner class to take input.
        Scanner scan = new Scanner(System.in);

        // Objects of polynomial class (Linked list) to store expressions.
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        // input expression in the form of string.
        String equation1 = scan.nextLine();
        String equation2 = scan.nextLine();

        // stringToLinkedList method to convert given string to linked list of polynomial.
        polynomial.stringToLinkedList(equation1);
        polynomial1.stringToLinkedList(equation2);

        // resultantPolynomial to store addition of two expressions in linked list of polynomial type.
        Polynomial resultantPolynomial = new Polynomial();
        resultantPolynomial.addPolynomial(polynomial, polynomial1);

        // Display polynomial equation on console.
        resultantPolynomial.display();
    }
}
