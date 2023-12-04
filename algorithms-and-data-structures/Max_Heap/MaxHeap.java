import java.util.NoSuchElementException;

public class MaxHeap implements IMyPriorityQueue {
    private int[] heap;
    private int size;

    // Required for Unit-Testing, do not remove!
    public int[] getHeap() {
        return heap;
    }

    /**
     * Use this constructor to create the MaxHeap bottom-up and in-place.
     *
     * @throws IllegalArgumentException if list is null;
     */
    public MaxHeap(int[] list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("List is null");
        }
        heap = list;
        size = list.length;
        buildHeap();
    }

    public int size() {
        return this.size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method removes and returns the max element from the PQ.
     *
     * @return the max element of the PQ.
     * @throws NoSuchElementException if the PQ is empty.
     */
    @Override
    public int removeMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty!");
        }
        int max = heap[0];
        heap[0] = heap[--size];
        downHeap(0);
        return max;
    }

    /**
     * Checks if the element val is already stored.
     *
     * @param val is the key to be searched
     * @return true if val is found, false otherwise (or if heap is empty).
     */
    @Override
    public boolean contains(int val) {
        return containsHelper(val, 0);
    }

    /**
     * This method sorts the given int[] list in-place using the constructor MaxHeap(int[] list).
     *
     * @param list contains the elements to be sorted in-place.
     * @throws IllegalArgumentException if list is null.
     */
    public static void sort(int[] list) throws IllegalArgumentException {
        MaxHeap maxHeap = new MaxHeap(list);
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = maxHeap.removeMax();
        }
    }

    private boolean containsHelper(int val, int index) {
        if (index >= size) {
            return false;
        }
        if (heap[index] == val) {
            return true;
        }
        return containsHelper(val, leftChild(index)) || containsHelper(val, rightChild(index));
    }

    private void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            downHeap(i);
        }
    }

    private void downHeap(int index) {
        while (true) {
            int leftChildIndex = leftChild(index);
            int rightChildIndex = rightChild(index);
            if (leftChildIndex >= size) {
                break;
            }
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex < size && heap[rightChildIndex] > heap[leftChildIndex]) {
                maxChildIndex = rightChildIndex;
            }
            if (heap[maxChildIndex] > heap[index]) {
                swap(index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
