public class StudentBST {
    private BSTNode root;

    public void clear() {
        root = null;
    }

    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    private BSTNode insertRecursive(BSTNode node, Student student) {
        if (node == null) return new BSTNode(student);

        int comparison = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());

        if (comparison < 0) node.left = insertRecursive(node.left, student);
        else if (comparison > 0) node.right = insertRecursive(node.right, student);

        return node;
    }

    public Student search(String id) {
        BSTNode current = root;

        while (current != null) {
            int comparison = id.compareToIgnoreCase(current.data.getStudentId());
            if (comparison == 0) return current.data;
            current = comparison < 0 ? current.left : current.right;
        }

        return null;
    }

    public void displayInOrder() {
        System.out.println("\n--- Students using BST (In-Order by Student ID) ---");
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}
