package estruturas;

public class Fila<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node comeco, fim;

    public void enqueue(T data) {
        Node newNode = new Node(data);
        if (fim == null) {
            comeco = fim = newNode;
        } else {
            fim.next = newNode;
            fim = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Fila está vazia");
        }
        T data = comeco.data;
        comeco = comeco.next;
        if (comeco == null) {
            fim = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return comeco == null;
    }
}
