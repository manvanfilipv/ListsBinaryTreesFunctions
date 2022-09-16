
public class Queue<T> {
    private class Data {
	T data;
	Data next;
        int min;

	Data(T item) {
            data = item;
	}
    }

    Data front, back;

    void enqueue(T item) {
	if (item == null) return;
	Data d = new Data(item);
	if (isEmpty()) {
            front = back = d;
	} else {
            back.next = d;
            back = d;
	}
    }
	
    T dequeue() {
	if (front == null) return null;
	Data d = front;
	front = d.next;
	return d.data;
    }
	
    boolean isEmpty() {
        return (front == null);
    }

}
