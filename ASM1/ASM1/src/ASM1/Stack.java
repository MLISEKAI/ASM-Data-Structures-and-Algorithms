/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM1;

/**
 *
 * @author admin
 */
public class Stack {
    private int[] stackArray;  
    private int top;          
    private int maxSize;      

   
    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1; 
    }

    
    public void push(int value) {
        if (top == maxSize - 1) {
            System.out.println("Stack Overflow! Cannot push " + value);
        } else {
            stackArray[++top] = value;
            System.out.println("Pushed: " + value);
        }
    }

    
    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow! Cannot pop.");
            return -1;
        } else {
            return stackArray[top--];
        }
    }

   
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty! Nothing to peek.");
            return -1;
        } else {
            return stackArray[top];
        }
    }

    
    public boolean isEmpty() {
        return top == -1;
    }
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(7);
        stack.push(10);
        stack.push(14);
        stack.push(9);

        System.out.println("Top element: " + stack.peek());

        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Is stack empty? " + stack.isEmpty());

        System.out.println("Popped: " + stack.pop());
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
