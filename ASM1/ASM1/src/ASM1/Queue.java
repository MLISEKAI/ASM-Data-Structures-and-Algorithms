/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM1;

/**
 *
 * @author admin
 */
class ArrayQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(int item) throws Exception {
        if (size == capacity) {
            throw new Exception("Queue is full");
        }
        queue[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
    }

    public int dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("Queue is empty");
        }
        int item = queue[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public int peek() throws Exception {
        if (size == 0) {
            throw new Exception("Queue is empty");
        }
        return queue[front];
    }
}


