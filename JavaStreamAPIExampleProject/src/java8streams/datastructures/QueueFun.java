package java8streams.datastructures;

public final class QueueFun<T> {
    private final ListFun<T> front;
    private final ListFun<T> rear;

    private QueueFun() {
        this.front = ListFun.list();
        this.rear = ListFun.list();
    }

    //Empty Queue
    public static <T> QueueFun<T> queue() {
        return new QueueFun<T>();
    }

    private QueueFun(QueueFun<T> queue, T element) {
        boolean frontIsEmpty = queue.front.isEmpty();
        this.front = frontIsEmpty ? queue.front.addEle(element) : queue.front;
        this.rear = frontIsEmpty ? queue.rear : queue.rear.addEle(element);
    }

    public QueueFun<T> enqueue(T t) {
        return new QueueFun<T>(this, t);
    }

    private QueueFun(ListFun<T> front, ListFun<T> rear) {
        boolean frontIsEmpty = front.isEmpty();
        this.front = frontIsEmpty ? rear.reverseList() : front;
        this.rear = frontIsEmpty ? front : rear;
    }

    public QueueFun<T> dequeue() {
        return new QueueFun<T>(this.front.tail(), rear);
    }
}
