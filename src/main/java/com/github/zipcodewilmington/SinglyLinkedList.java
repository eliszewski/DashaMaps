package com.github.zipcodewilmington;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    private int size;


    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = current.getValue();
                    current = current.getNextNode();
                    return data;
                }
                return null;
            }
        };
    }


    public class Node<T> {
        T value;
        Node<T> nextNode;

        public Node(T value) {
            this.value = value;
            this.nextNode = null; //set default to null
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    public void add(T valueToAdd) {
        Node<T> node = new Node<T>(valueToAdd);
        if (getHead() == null) setHead(node);
        else {
            this.tail.setNextNode(node);
        }
        this.setTail(node);
        size++;
    }

    public T get(int index) {
        int counter = 0;
        Node<T> currentNode = head;
        while (counter != index) {
            currentNode = currentNode.getNextNode();
            counter++;
        }
        return currentNode.getValue();
    }

    public boolean contains(T value) {
        Node<T> currentNode = getHead();
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) return true;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    public int find(T valueToFind) {
        Node<T> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.getValue().equals(valueToFind)) return index;
            else {
                index++;
                currentNode = currentNode.getNextNode();
            }
        }
        return -1;
    }

    public int size() {
        return getSize();
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> theCopy = new SinglyLinkedList<T>();
        Node<T> currentNode = this.head;
        while (currentNode != null) {
            theCopy.add(currentNode.getValue());
            currentNode = currentNode.getNextNode();
        }
        return theCopy;
    }

    public void remove(int index) {
        T valueToRemove = get(index);
        if (index == 0) this.setHead(head.nextNode); // 0 index case
        else {
            Node<T> currentNode = this.head;
            //iterate to the one before index
            while (valueToRemove != currentNode.getNextNode().getValue()) {
                currentNode = currentNode.getNextNode();
            }
            //last index case
            if (currentNode.getNextNode().getNextNode() == null) {
                currentNode.setNextNode(null);
                setTail(currentNode);
            }
            //change node before to point to the next one after the next
            else {
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
            }
        }
        size--;
    }
    //need an equals method

    public boolean equals(SinglyLinkedList<T> sllToCompare) {
        Node<T> currentNode1 = this.head;
        Node<T> currentNode2 = sllToCompare.getHead();
        if (this.size() != sllToCompare.size()) return false;
        else {
            while (currentNode1 != null) {
                if (!currentNode1.getValue().equals(currentNode2.getValue())) return false;
                currentNode1 = currentNode1.getNextNode();
                currentNode2 = currentNode2.getNextNode();
            }
            return true;
        }
    }
}

