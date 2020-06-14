/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author carlos.torra
 */


class TestReadLine {
  public static void main(String[] args) {
    BufferedReader in = new EditableBufferedReader(
      new InputStreamReader(System.in));
    String str = null;
        try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\n\rline is: " + str);
    //A partir d'aquí hem decidit fer una calculadora bàsica a partir de la classe Scanner
    double num1;
      double num2;
      double ans;
      char op;
      Scanner reader = new Scanner(System.in);
      System.out.println("CALCULATOR");
      System.out.print("Enter first number: ");
      num1 = reader.nextDouble();
      System.out.println(num1);
      System.out.print("Enter second number: ");
      num2 = reader.nextDouble();
      System.out.println(num2);
      System.out.print("Enter an operator (+, -, *, /): ");
      op = reader.next().charAt(0);
      System.out.println(op);
      switch(op) {
         case '+': ans = num1 + num2;
            break;
         case '-': ans = num1 - num2;
            break;
         case '*': ans = num1 * num2;
            break;
         case '/': ans = num1 / num2;
            break;
         default:  System.out.printf("Error! Enter correct operator");
            return;
      }
      System.out.print("\nThe result is --> ");
      System.out.println(num1 + " " + op + " " + num2 + " = " + ans);
  }
}
