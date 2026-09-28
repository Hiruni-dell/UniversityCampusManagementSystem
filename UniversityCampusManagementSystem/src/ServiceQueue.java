public class ServiceQueue {
    private static class QueueNode {
        ServiceRequest data;
        QueueNode next;

        QueueNode(ServiceRequest data) {
            this.data = data;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public void add(ServiceRequest request) {
        QueueNode node = new QueueNode(request);

        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public ServiceRequest processNext() {
        if (front == null) return null;

        ServiceRequest request = front.data;
        front = front.next;

        if (front == null) rear = null;
        size--;
        return request;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.println("\n--- Service Request Queue (FIFO) ---");

        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        QueueNode current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
