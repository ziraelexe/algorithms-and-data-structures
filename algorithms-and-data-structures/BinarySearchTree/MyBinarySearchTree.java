import java.util.ArrayList;
import java.util.Random;
public class MyBinarySearchTree implements IBinarySearchTree {
    public int numOfComparisons = 0;

    /**
     * Root of the binary search tree.
     */
    protected BinaryTreeNode treeRoot;

    /**
     * Number of elements stored in the binary search tree.
     */
    protected int treeSize;

    /**
     * Initialization of the phone book based on a BST.
     */
    public MyBinarySearchTree() {
        treeRoot = null;
        treeSize = 0;
    }

    // This constructor is used for corrections - DO NOT CHANGE IT!
    public MyBinarySearchTree(BinaryTreeNode root, int size) {
        treeRoot = root;
        treeSize = size;
    }


    @Override
    public BinaryTreeNode getRoot() {
        return this.treeRoot;
    }

    @Override
    public void insert(int key, String elem) throws IllegalArgumentException {
        if (key < 0)
            throw new IllegalArgumentException("Key is < 0");
        if (elem == null) throw new IllegalArgumentException();

        BinaryTreeNode newNode = new BinaryTreeNode(key, elem);
        if (!insert(treeRoot, newNode)) {
            treeRoot = newNode;
            treeSize++;
        }
    }

    @Override
    public String find(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key is < 0");
        }
        BinaryTreeNode node = treeRoot;
        while (node != null) {
            numOfComparisons++;
            if (node.key == key) {
                return node.elem;
            } else if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public boolean remove(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key is < 0");
        }
        BinaryTreeNode node = treeRoot;
        BinaryTreeNode parent = null;
        boolean isLeftChild = false;
        while (node != null) {
            if (node.key == key) {
                break;
            } else if (node.key > key) {
                parent = node;
                node = node.left;
                isLeftChild = true;
            } else {
                parent = node;
                node = node.right;
                isLeftChild = true;
            }
        }
        if (node == null) {
            return false;
        }
        // Case 1: Node is a leaf node
        if (node.left == null && node.right == null) {
            if (node == treeRoot) {
                treeRoot = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            treeSize--;
        }
        // Case 2: Node has one child
        else if (node.left == null) {
            if (node == treeRoot) {
                treeRoot = node.right;
            } else if (isLeftChild) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
            treeSize--;
        } else if (node.right == null) {
            if (node == treeRoot) {
                treeRoot = node.left;
            } else if (isLeftChild) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
            treeSize--;
        }
        //Case 3: Node has two children
        else {
            BinaryTreeNode successor = getSuccessor(node);
            if (node == treeRoot) {
                treeRoot = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = node.left;
            treeSize--;
        }
        return true;
    }

    //Helper method for remove
    private BinaryTreeNode getSuccessor(BinaryTreeNode node) {
        BinaryTreeNode parent = node;
        BinaryTreeNode successor = node;
        BinaryTreeNode curr = node.right;
        while (curr != null) {
            parent = successor;
            successor = curr;
            curr = curr.left;
        }
        if (successor != node.right) {
            parent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    @Override
    public int size() {
        return treeSize;
    }

    @Override
    public int[] toArrayPostOrder() {
        int[] postOrderArr = new int[treeSize];
        traversePostOrder(treeRoot, postOrderArr, 0);
        return postOrderArr;
    }

    //Helper method for toArrayPostOrder
    private int traversePostOrder(BinaryTreeNode node, int[] arr, int index) {
        if (node == null) {
            return index;
        }
        index = traversePostOrder(node.left, arr, index);
        index = traversePostOrder(node.right, arr, index);
        arr[index++] = node.key;
        return index;
    }

    @Override
    public int[] toArrayInOrder() {
        int[] inOrderArr = new int[treeSize];
        traverseInOrder(treeRoot, inOrderArr, 0);
        return inOrderArr;
    }

    //Helper method for toArrayInOrder
    private int traverseInOrder(BinaryTreeNode node, int[] arr, int index) {
        if (node == null) {
            return index;
        }
        index = traverseInOrder(node.left, arr, index);
        arr[index++] = node.key;
        index = traverseInOrder(node.right, arr, index);
        return index;
    }

    @Override
    public int[] toArrayPreOrder() {
        int[] preOrderArr = new int[treeSize];
        traversePreOrder(treeRoot, preOrderArr, 0);
        return preOrderArr;
    }

    //Helper method for toArrayPreOrder
    private int traversePreOrder(BinaryTreeNode node, int[] arr, int index) {
        if (node == null) {
            return index;
        }
        arr[index++] = node.key;
        index = traversePreOrder(node.left, arr, index);
        index = traversePreOrder(node.right, arr, index);
        return index;
    }

    @Override
    public String getParent(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key < 0");
        }
        BinaryTreeNode node = search(key);
        if (node == null) {
            return null;
        } else if (node == treeRoot) {
            return node.elem;
        } else {
            return node.parent.elem;
        }
    }

    //Helper method for getParent
    private BinaryTreeNode search(int key) {
        if (key < 0) {
            throw new IllegalArgumentException("Key < 0");
        }
        BinaryTreeNode node = treeRoot;
        while (node != null) {
            if (key == node.key) {
                return node;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public boolean isRoot(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key is < 0");
        }
        return treeRoot.key == key;
    }

    @Override
    public boolean isInternal(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key is < 0");
        }
        BinaryTreeNode node = treeRoot;
        while (node != null) {
            if (node.key == key) {
                break;
            } else if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return false;
        }
        return (node.left != null) || (node.right != null);
    }

    @Override
    public boolean isExternal(int key) throws IllegalArgumentException {
        if (key < 0) {
            throw new IllegalArgumentException("Key is < 0");
        }
        BinaryTreeNode node = treeRoot;
        while (node != null) {
            if (node.key == key) {
                break;
            } else if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return false;
        }
        return (node.left == null) && (node.right == null);
    }

    @Override
    public boolean isBST(IBinarySearchTree bst) throws IllegalArgumentException {
        if (bst == null) {
            throw new IllegalArgumentException("Binary Search Tree cannot be null");
        }
        //Check if bst is binary search tree
        return isBSTHelper(bst.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //Helper method for isBST
    private boolean isBSTHelper(BinaryTreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        int key = node.key;

        //check if key violates BST requirements
        if (key < min || key > max) {
            return false;
        }
        return isBSTHelper(node.left, min, key - 1) && isBSTHelper(node.right, key + 1, max);
    }


    @Override
    public int[] runtimeComparison(ArrayList<Integer> list, int key) throws IllegalArgumentException {
        MyBinarySearchTree bst = new MyBinarySearchTree();
        for (int i = 0; i < list.size(); i++) {
            bst.insert(list.get(i), "");
        }

        int bstComparisons = 1;
        BinaryTreeNode node = bst.getRoot();
        while (node != null && node.key != key) {
            bstComparisons += 2;
            if (node.key == key) {
                break;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        int listComparisons = 0;
        if (list.contains(key)) {
            for (int i = 0; i < list.size(); i++) {
                listComparisons++;
                if (list.get(i) == key) {
                    break;
                }
            }
        }
        return new int[]{bstComparisons, listComparisons};
    }

    public String returnMinKey() {
        if (treeRoot == null) {
            return null;
        }
        BinaryTreeNode node = treeRoot;
        while (node.left != null) {
            node = node.left;
        }
        return Integer.toString(node.key);
    }

    public int getDepth(int key) throws IllegalArgumentException {
        BinaryTreeNode node = findNode(treeRoot, key);
        if (node == null) {
            throw new IllegalArgumentException("Node with key not found");
        }
        int depth = 0;
        while (node != treeRoot) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    //Helper method for getDepth
    private BinaryTreeNode findNode(BinaryTreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node;
        }
        if (key < node.key) {
            return findNode(node.left, key);
        } else {
            return findNode(node.right, key);
        }
    }

    public boolean isTreeComplete() {
        return false;
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    //----- PRIVATE METHODS --------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    private boolean insert(BinaryTreeNode node, BinaryTreeNode x) {
        while (node != null) {
            int compare = node.key.compareTo(x.key);
            if (compare == 0) {
                return true;
            } else if (compare > 0) {
                if (node.left == null) {
                    node.left = x;
                    x.parent = node;
                    treeSize++;
                    return true;
                } else
                    node = node.left;
            } else {
                if (node.right == null) {
                    node.right = x;
                    x.parent = node;
                    treeSize++;
                    return true;
                } else
                    node = node.right;
            }
        }
        return false;
    }
}