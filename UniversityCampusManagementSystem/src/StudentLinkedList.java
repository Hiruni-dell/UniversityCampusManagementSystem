public class StudentLinkedList {
    private StudentNode head;

    public boolean containsId(String id) {
        StudentNode current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) return true;
            current = current.next;
        }
        return false;
    }

    public boolean add(Student student) {
        if (containsId(student.getStudentId())) return false;

        StudentNode node = new StudentNode(student);
        if (head == null) {
            head = node;
            return true;
        }

        StudentNode current = head;
        while (current.next != null) current = current.next;
        current.next = node;
        return true;
    }

    public Student search(String id) {
        StudentNode current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) return current.data;
            current = current.next;
        }
        return null;
    }

    public boolean update(String id, String name, String programme, double marks) {
        Student student = search(id);
        if (student == null) return false;
        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);
        return true;
    }

    public Student delete(String id) {
        StudentNode current = head;
        StudentNode previous = null;

        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) {
                if (previous == null) head = current.next;
                else previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student Records (Linked List) ---");
        StudentNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public java.util.List<Student> toList() {
        java.util.List<Student> students = new java.util.ArrayList<>();
        StudentNode current = head;
        while (current != null) {
            students.add(current.data);
            current = current.next;
        }
        return students;
    }

    public int size() {
        int count = 0;
        StudentNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
