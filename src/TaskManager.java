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
        Task target = findByPriority(taskBST.root, priority);
        if (target == null) throw new IllegalArgumentException("NO NODE FOUND!!!");
        taskBST.delete(target);
        System.out.println("Task Eliminated: " + target + " [Priority: " + priority + "]");
    }

    public boolean searchByPriority(int priority) {
        return findByPriority(taskBST.root, priority) != null;
    }

    // Print used reverse inorder because it prints better. top to bottom in terminal
    public void printReverseInorder() {
        System.out.println("Tasks in priority order:");
        taskBST.reverseInorder();
    }

    // Find first by priority
    private Task findByPriority(BST.TreeNode<Task> node,  int priority) {
        if (node == null) return null;
        int nodePriority = node.element.getPriority();
        if (priority < nodePriority) return findByPriority(node.left, priority);
        else if (priority > nodePriority) return findByPriority(node.right, priority);
        else return node.element;
    }

    public int height() {
        return taskBST.height();
    }


    public static void main(String[] args) {
        // TO IMPLEMENT: Create a TaskManager object
        // Use the TaskManager to insert tasks, search, delete, and print in priority order
        // This serves as your main test harness
        TaskManager taskManager = new TaskManager();

        taskManager.addTask(10, "1. wake up");
        taskManager.addTask(7, "2. Walk around");
        taskManager.addTask(3, "3. Look at birds");
        taskManager.addTask(8, "4. Eat");
        taskManager.addTask(2, "5. Look at goldfish");

        taskManager.printReverseInorder();

        System.out.println("======Tree Height======");
        System.out.println(taskManager.height());
        System.out.println();

        System.out.println("======Search By Priority======");
        System.out.println(taskManager.searchByPriority(2));
        System.out.println(taskManager.searchByPriority(1));
        System.out.println();

        System.out.println("======Delete By Priority======");
        taskManager.deleteByFirstPriority(7);
        taskManager.deleteByFirstPriority(2);

        System.out.println();
        taskManager.printReverseInorder();
    }
}
