package estruturas;

public class Pilha<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node top; // topo da pilha

    // adiciona um elemento
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    // remove e retorna o elemento do topo
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha está vazia");
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
