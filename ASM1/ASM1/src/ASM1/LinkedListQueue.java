/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM1;

/**
 *
 * @author admin
 */

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListQueue {
    private Node front;
    private Node rear;

    public LinkedListQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(int item) {
        Node newNode = new Node(item);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public int dequeue() throws Exception {
        if (front == null) {
            throw new Exception("Queue is empty");
        }
        int dequeuedValue = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return dequeuedValue;
    }

    public boolean isEmpty() {
        return front == null;
    }
    
    public int peek() throws Exception {
        if (front == null) {
            throw new Exception("Queue is empty");
        }
        return front.data;
    }
}
