public class StudentBST {
    private BSTNode root;
    private int size;

    public void clear() {
        root = null;
        size = 0;
    }

    public void insert(Student student) {
        if (student == null) return;
        int previousSize = size;
        root = insertRecursive(root, student);
        if (size == previousSize && root != null) {
            // Duplicate IDs are ignored by the BST.
        }
    }

    private BSTNode insertRecursive(BSTNode node, Student student) {
        if (node == null) {
            size++;
            return new BSTNode(student);
        }

        int comparison = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());

        if (comparison < 0) node.left = insertRecursive(node.left, student);
        else if (comparison > 0) node.right = insertRecursive(node.right, student);

        return node;
    }

    public Student search(String id) {
        if (id == null) return null;

        BSTNode current = root;

        while (current != null) {
            int comparison = id.trim().compareToIgnoreCase(current.data.getStudentId());
            if (comparison == 0) return current.data;
            current = comparison < 0 ? current.left : current.right;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public void displayInOrder() {
        System.out.println("\n--- Students using BST (In-Order by Student ID) ---");
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        inOrder(root);
        System.out.println("Total BST records: " + size);
    }

    private void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}