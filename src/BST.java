// BST.java
// This class defines a generic Binary Search Tree (BST) data structure.
// It uses a nested TreeNode class to represent each node in the tree.
// The tree stores elements of any type E that implements Comparable<E>.
public class BST<E extends Comparable<E>> {
    protected TreeNode<E> root; // The root node of the BST
    protected int size = 0;     // Keeps track of the number of nodes in the tree

    // Default constructor for an empty tree
    public BST() {}

    public void insert(E element) {
        root = insert(root, element);
    }

    private TreeNode<E> insert(TreeNode<E> node,  E element) {
        if (node == null) {
            size++;
            return new TreeNode<>(element);
        }
        int compare = element.compareTo(node.element);
        if (compare < 0) node.left = insert(node.left, element); // less than root node = left
        else if (compare > 0) node.right = insert(node.right, element); // more than root node = right
        return node; // dupe = return node
    }

    public boolean search(E element) {
        return search(root, element);
    }

    private boolean search(TreeNode<E> node,  E element) {
        if (node == null) return false;
        int compare = element.compareTo(node.element);
        if (compare < 0) return search(node.left, element); // if comparative is less than 0 we search down left side
        else if (compare > 0) return search(node.right, element); // if comparative is more than 0 we search down right side
        else return true;
    }

    public void delete(E element) {
        if (search(element)) {
            root = delete(root, element);
            size--;
        }
    }

    private TreeNode<E> delete(TreeNode<E> node,  E element) {
        if (node == null) return null;
        int compare = element.compareTo(node.element);
        if (compare < 0) node.left = delete(node.left, element);
        else if (compare > 0) node.right = delete(node.right, element);
        else {
            // Found node to delete - 3 cases
            if (node.left == null) return node.right; // case 1: no left child
            if (node.right == null) return node.left; // Case 2: no right child
            TreeNode<E> successor = findMin(node.right); // Case 3: 2 children - replace with inorder successor
            node.element = successor.element;
            node.right = delete(node.right, successor.element); // Delete the successor from the right subtree
        }
        return node;
    }

    private TreeNode<E> findMin(TreeNode<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Public method to get the height of the tree
    public int height() {
        return height(root);
    }

    // Private helper method to compute the height recursively
    private int height(TreeNode<E> node) {
        if (node == null) return 0; // Base case: empty subtree
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Public method to perform inorder traversal
    public void inorder() {
        inorder(root);
        System.out.println(); // Print a newline after traversal output
    }

    // Recursive helper for inorder traversal
    private void inorder(TreeNode<E> root) {
        if (root != null) {
            inorder(root.left);             // Visit left subtree
            System.out.print(root.element + " "); // Visit current node
            inorder(root.right);            // Visit right subtree
        }
    }

    // Public method to perform reverse inorder traversal
    public void reverseInorder() {
        reverseInorder(root);
        System.out.println();
    }

    // Recursive helper for reverse inorder traversal
    private void reverseInorder(TreeNode<E> root) {
        if (root != null) {
            reverseInorder(root.right);            // Visit right subtree
            System.out.println(root.element + " "); // Visit current node
            reverseInorder(root.left);             // Visit left subtree
        }
    }

    // Nested TreeNode class to represent each node in the tree
    public static class TreeNode<E> {
        protected E element;        // The data stored at this node
        protected TreeNode<E> left; // Reference to the left child
        protected TreeNode<E> right;// Reference to the right child

        // Constructor initializes the node with data
        public TreeNode(E e) {
            element = e;
        }
    }

    // Method to get the number of nodes in the BST
    public int size() {
        return size;
    }
}
