public class MySortedDoublyLinkedList implements IMyList {

    private MyListNode head;
    private MyListNode tail;
    private int size;


    /**
     * Constructor.
     * Initializes List.
     */
    public MySortedDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MySortedDoublyLinkedList(MyListNode newHead, MyListNode newTail, int newSize) {
        head = newHead;
        tail = newTail;
        size = newSize;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void insertElem(int val) {
        MyListNode newNode = new MyListNode(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        MyListNode curr = head;
        while (curr != null && curr.getElement() < val) {
            curr = curr.getNext();
        }

        if (curr == null) {
            newNode.setPrev(tail);
            tail.setNext((newNode));
            tail = newNode;
        } else if (curr.getElement() == val) {
            if (curr == head) {
                newNode.setNext(curr);
                curr.setPrev(newNode);
                head = newNode;
            } else {
                newNode.setPrev(curr.getPrev());
                curr.getPrev().setNext(newNode);
                newNode.setNext(curr);
                curr.setPrev(newNode);
            }
        } else {
            newNode.setPrev(curr.getPrev());
            if (curr.getPrev() != null) {
                curr.getPrev().setNext(newNode);
            } else {
                head = newNode;
            }
            newNode.setNext(curr);
            curr.setPrev(newNode);
        }
        size++;
    }

    @Override
    public int getElem(int index) throws IllegalArgumentException {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        MyListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public boolean removeFirst(int val) {
        MyListNode curr = head;
        while (curr != null) {
            if (curr.getElement() == val) {
                if (curr == head) {
                    head = curr.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    } else {
                        tail = null;
                    }
                } else if (curr == tail) {
                    tail = curr.getPrev();
                    tail.setNext(null);
                } else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                }
                size--;
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public boolean removeAll(int val) {
        boolean removed = false;
        MyListNode curr = head;
        while (curr != null) {
            if (curr.getElement() == val) {
                if (curr == head) {
                    head = curr.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    } else {
                        tail = null;
                    }
                } else if (curr == tail) {
                    tail = curr.getPrev();
                    tail.setNext(null);
                } else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                }
                size--;
                removed = true;
            }
            curr = curr.getNext();
        }
        return removed;
    }

    @Override
    public void removeDuplicates() {
        if (head == null || head == tail) {
            return;
        }
        MyListNode curr = head;
        int temp = curr.getElement();
        curr = curr.getNext();

        while (curr != null) {
            if (temp != curr.getElement()) {
                temp = curr.getElement();
            } else {
                curr.getPrev().setNext(curr.getNext());
                if (curr != tail) {
                    curr.getNext().setPrev(curr.getPrev());
                } else {
                    tail = curr.getPrev();
                }
                size--;
            }
            curr = curr.getNext();
        }
    }

    @Override
    public void filterNMax(int numOfElements) throws IllegalArgumentException {
        if (numOfElements <= 0 || numOfElements > size) {
            throw new IllegalArgumentException("Invalid number of elements");
        }
        int nElements = size - numOfElements;
        MyListNode curr = head;
        for (int i = 0; i < nElements; i++) {
            curr = curr.getNext();
        }
        if (curr == head) {
            return;
        }
        head = curr;
        curr.setPrev(null);

        size = numOfElements;
    }

    @Override
    public void filterOdd() {
        MyListNode curr = head;
        while (curr != null) {
            if (curr.getElement() % 2 == 0) {
                removeFirst(curr.getElement());
            }
            curr = curr.getNext();
        }
    }

    @Override
    public int searchValue(int val) {
        MyListNode curr = head;
        int index = 0;

        while (curr != null) {
            if (curr.getElement() == val) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    /***********************************************************
     * DON'T TOUCH THIS CODE
     * Methods for testing your code after submission.
     ***********************************************************/
    public MyListNode getHead() {
        return head;
    }

    public MyListNode getTail() {
        return tail;
    }
}
