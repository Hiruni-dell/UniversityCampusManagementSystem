import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentLinkedList studentList = new StudentLinkedList();
    private static final ActionStack actionStack = new ActionStack();
    private static final ServiceQueue serviceQueue = new ServiceQueue();
    private static final StudentBST studentBST = new StudentBST();
    private static final StudentHashTable hashTable = new StudentHashTable(17);
    private static final CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        seedDemoData();
        boolean running = true;

        System.out.println("====================================================");
        System.out.println(" UNIVERSITY STUDENT & CAMPUS MANAGEMENT SYSTEM");
        System.out.println("====================================================");
        System.out.println("Demo data has been loaded for testing.");
        System.out.println("You may add, update, delete, search and manage records.");

        while (running) {
            displayMenu();
            int choice = InputValidator.readMenuChoice(scanner, 1, 16);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> studentList.display();
                case 5 -> addServiceRequest();
                case 6 -> processServiceRequest();
                case 7 -> actionStack.display();
                case 8 -> studentBST.displayInOrder();
                case 9 -> searchStudentUsingHashing();
                case 10 -> addCampusLocation();
                case 11 -> removeCampusLocation();
                case 12 -> addCampusConnection();
                case 13 -> removeCampusConnection();
                case 14 -> campusGraph.displayConnections();
                case 15 -> traverseCampus();
                case 16 -> running = false;
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        System.out.println("\nThank you for using the system.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n====================================================");
        System.out.println("                    MAIN MENU");
        System.out.println("====================================================");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS/DFS");
        System.out.println("16. Exit");
        System.out.println("====================================================");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student Record ---");

        String id = InputValidator.readNonEmpty(scanner, "Student ID: ");
        if (studentList.containsId(id)) {
            System.out.println("Duplicate Student ID. Record was not added.");
            return;
        }

        String name = InputValidator.readNonEmpty(scanner, "Name: ");
        String programme = InputValidator.readNonEmpty(scanner, "Programme: ");
        double marks = InputValidator.readMarks(scanner);

        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        rebuildIndexes();

        actionStack.push("Added student record: " + id);
        System.out.println("Student record added successfully.");
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student Record ---");

        String id = InputValidator.readNonEmpty(scanner, "Enter Student ID to update: ");
        Student existing = studentList.search(id);

        if (existing == null) {
            System.out.println("Student record not found.");
            return;
        }

        String name = InputValidator.readNonEmpty(scanner, "New Name: ");
        String programme = InputValidator.readNonEmpty(scanner, "New Programme: ");
        double marks = InputValidator.readMarks(scanner);

        studentList.update(id, name, programme, marks);
        rebuildIndexes();

        actionStack.push("Updated student record: " + id);
        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student Record ---");

        String id = InputValidator.readNonEmpty(scanner, "Enter Student ID to delete: ");
        Student deleted = studentList.delete(id);

        if (deleted == null) {
            System.out.println("Student record not found.");
            return;
        }

        rebuildIndexes();
        actionStack.push("Deleted student record: " + deleted.getStudentId());
        System.out.println("Student record deleted successfully.");
        System.out.println("Deleted: " + deleted);
    }

    private static void addServiceRequest() {
        System.out.println("\n--- Add Service Request ---");

        String id = InputValidator.readNonEmpty(scanner, "Student ID: ");
        if (studentList.search(id) == null) {
            System.out.println("Student ID not found. Add the student record first.");
            return;
        }

        String description = InputValidator.readNonEmpty(scanner, "Request description: ");
        ServiceRequest request = new ServiceRequest(id, description);
        serviceQueue.add(request);

        actionStack.push("Added service request #" + request.getRequestNumber());
        System.out.println("Service request added to the queue.");
        System.out.println(request);
    }

    private static void processServiceRequest() {
        System.out.println("\n--- Process Next Service Request ---");

        ServiceRequest request = serviceQueue.processNext();
        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }

        actionStack.push("Processed service request #" + request.getRequestNumber());
        System.out.println("Processing: " + request);
        System.out.println("Request processed successfully.");
    }

    private static void searchStudentUsingHashing() {
        System.out.println("\n--- Search Student using Hashing ---");

        String id = InputValidator.readNonEmpty(scanner, "Enter Student ID: ");
        Student student = hashTable.get(id);

        if (student == null) {
            System.out.println("Student not found in hash table.");
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static void addCampusLocation() {
        System.out.println("\n--- Add Campus Location ---");

        String location = InputValidator.readNonEmpty(scanner, "Location name: ");

        if (campusGraph.addLocation(location)) {
            actionStack.push("Added campus location: " + location);
            System.out.println("Campus location added successfully.");
        } else {
            System.out.println("Duplicate location or invalid location. Nothing was added.");
        }
    }

    private static void removeCampusLocation() {
        System.out.println("\n--- Remove Campus Location ---");

        String location = InputValidator.readNonEmpty(scanner, "Location name: ");

        if (campusGraph.removeLocation(location)) {
            actionStack.push("Removed campus location: " + location);
            System.out.println("Campus location and its connections removed successfully.");
        } else {
            System.out.println("Campus location not found.");
        }
    }

    private static void addCampusConnection() {
        System.out.println("\n--- Add Campus Connection/Road ---");

        String from = InputValidator.readNonEmpty(scanner, "From location: ");
        String to = InputValidator.readNonEmpty(scanner, "To location: ");

        if (!campusGraph.containsLocation(from) || !campusGraph.containsLocation(to)) {
            System.out.println("Both locations must exist before adding a connection.");
            return;
        }

        if (campusGraph.addConnection(from, to)) {
            actionStack.push("Added campus connection: " + from + " <-> " + to);
            System.out.println("Connection added successfully.");
        } else {
            System.out.println("Invalid connection, duplicate connection, or same location.");
        }
    }

    private static void removeCampusConnection() {
        System.out.println("\n--- Remove Campus Connection/Road ---");

        String from = InputValidator.readNonEmpty(scanner, "From location: ");
        String to = InputValidator.readNonEmpty(scanner, "To location: ");

        if (campusGraph.removeConnection(from, to)) {
            actionStack.push("Removed campus connection: " + from + " <-> " + to);
            System.out.println("Connection removed successfully.");
        } else {
            System.out.println("Connection not found or one/both locations do not exist.");
        }
    }

    private static void traverseCampus() {
        System.out.println("\n--- Campus Traversal ---");
        System.out.println("1. BFS");
        System.out.println("2. DFS");

        int choice = InputValidator.readMenuChoice(scanner, 1, 2);
        String start = InputValidator.readNonEmpty(scanner, "Starting location: ");

        if (choice == 1) {
            campusGraph.bfs(start);
        } else {
            campusGraph.dfs(start);
        }
    }

    private static void rebuildIndexes() {
        studentBST.clear();
        hashTable.clear();

        // Rebuild the secondary structures from the Linked List.
        // The list remains the primary student-record store.
        // A temporary traversal is achieved by collecting known records through
        // the public search/display API below.
        //
        // For this small console application, we rebuild from a maintained
        // snapshot supplied by getAllStudents().
        for (Student student : getAllStudents()) {
            studentBST.insert(student);
            hashTable.put(student);
        }
    }

    private static java.util.List<Student> getAllStudents() {
        // StudentLinkedList exposes a safe snapshot for secondary indexes.
        return studentList.toList();
    }

    private static void seedDemoData() {
        studentList.add(new Student("23DA2-0043", "E.A. Chamod Maleesha",
                "Data Science", 82));
        studentList.add(new Student("23DA2-0439", "Nushrath Ahamed",
                "Information Technology", 76));
        studentList.add(new Student("23DA2-0261", "W.H.D.S. Fernando",
                "Data Science", 88));
        studentList.add(new Student("23DA2-0346", "P.H.Yashodha Amashi",
                "Software Engineering", 91));
        rebuildIndexes();

        campusGraph.addLocation("Main Gate");
        campusGraph.addLocation("Library");
        campusGraph.addLocation("Faculty Block");
        campusGraph.addLocation("Student Center");
        campusGraph.addLocation("Laboratory");
        campusGraph.addLocation("Cafeteria");

        campusGraph.addConnection("Main Gate", "Student Center");
        campusGraph.addConnection("Main Gate", "Faculty Block");
        campusGraph.addConnection("Student Center", "Cafeteria");
        campusGraph.addConnection("Student Center", "Library");
        campusGraph.addConnection("Faculty Block", "Laboratory");
        campusGraph.addConnection("Faculty Block", "Library");

        actionStack.push("System initialized with demo data.");
    }
}
