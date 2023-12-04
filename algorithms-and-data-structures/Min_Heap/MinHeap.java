
import java.util.NoSuchElementException;

public class MinHeap implements IMyPriorityQueue {
    /********************************************************
     * REQUIRED FOR UNITTESTS! DO NOT REMOVE THIS METHOD
     ********************************************************/
    private int[] heap; // int-array that represents your heap
    private int size; // keeps track of heap size

    public int[] getHeap() {
        return heap;
    }

    /********************************************************/

    // Constructor that initially defines the allocated memory for the heap.
    public MinHeap(int initCapacity) {
        heap = new int[initCapacity];
        size = 0;
    }

    @Override
    public void insert(int val) {
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
        heap[size] = val;
        size++;
        upHeap(size - 1); // move inserted element to correct position in heap
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int min() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    @Override
    public int removeMin() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int min = heap[0];
        heap[0] = heap[--size];
        downHeap(0);
        return min;
    }

    @Override
    public int size() {
        return size;
    }

    private void upHeap(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (heap[index] < heap[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void downHeap(int index) {
        while (true) {
            int leftChildIndex = leftChild(index);
            int rightChildIndex = rightChild(index);
            if (leftChildIndex >= size) {
                break;
            }
            int minChildIndex = leftChildIndex;
            if (rightChildIndex < size && heap[rightChildIndex] < heap[leftChildIndex]) {
                minChildIndex = rightChildIndex;
            }
            if (heap[minChildIndex] < heap[index]) {
                swap(index, minChildIndex);
                index = minChildIndex;
            } else {
                break;
            }
        }
    }

    // get the index of parent of element at the given index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // get the index of left child of element at the given index
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // get the index of right child of element at the given index
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // swap elements at given index
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
