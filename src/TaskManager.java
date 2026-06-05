import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

// TaskManager.java
// This class will manage a collection of tasks using a BST<Task>.
// It should provide methods to add, delete, search, and print tasks by priority.
public class TaskManager {
    private BST<Task> taskBST = new BST<>();

    // Add
    public void addTask(int priority, String description) {
        taskBST.insert(new Task(priority, description));
    }

    // Delete
    public void deleteByFirstPriority(int priority) {
        Task target = findByFirstPriority(taskBST.root, priority);
        if (target == null) throw new IllegalArgumentException("NO NODE FOUND!!!");
        taskBST.delete(target);
        System.out.println("Target eliminated!");
    }

    public boolean searchByPriority( int priority) {
        return findByFirstPriority(taskBST.root, priority) != null;
    }

    // Print
    public void printInorder() {
        System.out.println("Tasks in priority order:");
        taskBST.inorder();
    }

    // Find first by priority
    private Task findByFirstPriority(BST.TreeNode<Task> node,  int priority) {
        if (node == null) throw new IllegalArgumentException("NO NODE FOUND!!!");
        int nodePriority = node.element.getPriority();
        if (nodePriority < 0) return findByFirstPriority(node.left, priority);
        else if (nodePriority > 0) return findByFirstPriority(node.right, priority);
        else return node.element;
    }

    public int height() {
        return taskBST.height();
    }


    public static void main(String[] args) {
        // TO IMPLEMENT: Create a TaskManager object
        // Use the TaskManager to insert tasks, search, delete, and print in priority order
        // This serves as your main test harness


    }
}
