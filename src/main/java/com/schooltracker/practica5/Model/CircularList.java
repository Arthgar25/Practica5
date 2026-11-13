package com.schooltracker.practica5.Model;
public class CircularList<T> {
    private Node<T> tail;
    private int size;

    public CircularList() {
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            tail = newNode;
            tail.setNext(tail);
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    public void addLast(T data) {
        addFirst(data);
        tail = tail.getNext();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> head = tail.getNext();
        T data = head.getData();
        if (tail == head) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> current = tail.getNext();
        T data = tail.getData();
        if (tail == current) {
            tail = null;
        } else {
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(tail.getNext());
            tail = current;
        }
        size--;
        return data;
    }
}