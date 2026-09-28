# University Student Record and Campus Route Management System

## CIT300 - Data Structures and Algorithms
### Graded Practical Assignment 1 (Week 10)

A Java console application demonstrating practical use of linear data structures, trees, hashing, and graphs for university student records and campus route management.

## Group Members

| Student ID | Name | Responsibility |
|---|---|---|
| 23DA2-0043 | E.A. Chamod Maleesha | Linked List and Student Record Management |
| 23DA2-0439 | Nushrath Ahamed | Stack and Queue |
| 23DA2-0261 | W.H.D.S. Fernando | BST/AVL and Hashing |
| 23DA2-0346 | P.H.Yashodha Amashi | Graph, Campus Connections and BFS/DFS |

## Individual Contributions

### E.A. Chamod Maleesha
- Implemented Student model and student nodes.
- Implemented linked-list storage.
- Implemented add, update, delete, search and display operations.

### Nushrath Ahamed
- Implemented action stack.
- Implemented service request and FIFO queue.
- Integrated recent-action logging and request processing.

### W.H.D.S. Fernando
- Implemented BST node and Student BST.
- Implemented hash table with separate chaining.
- Integrated Student ID searching using hashing.

### P.H.Yashodha Amashi
- Implemented campus graph using an adjacency list.
- Implemented location and connection operations.
- Implemented BFS and DFS traversal.

### All Members
- Integration, validation, testing, debugging, documentation and demonstration.

## Features

1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS/DFS
16. Exit

## Data Structures Used

### Linked List
The linked list is the primary storage structure for student records. Each node contains one Student object and a reference to the next node.

### Stack
The stack stores recent system actions using LIFO order. Examples include adding, updating and deleting students, and campus operations.

### Queue
Service requests are stored in a custom linked FIFO queue. The request that arrives first is processed first.

### Binary Search Tree
The BST organizes student records by Student ID. In-order traversal displays the records in Student ID order.

### Hash Table
Student IDs are hashed into table buckets. Separate chaining handles collisions. This supports efficient Student ID lookup.

### Graph
Campus locations are vertices and roads are undirected edges. The graph uses an adjacency list.

### BFS and DFS
BFS uses a queue to visit nearby locations level-by-level. DFS uses recursion to explore a path before backtracking.

## Input Validation

The application handles:
- Empty text input
- Invalid menu choices
- Non-numeric marks
- Marks outside 0-100
- Duplicate Student IDs
- Missing student records
- Missing campus locations
- Duplicate/invalid campus connections
- Attempts to process an empty service queue
- Invalid graph traversal starting locations

## How to Run in Eclipse

1. Open Eclipse.
2. Select `File > New > Java Project`.
3. Project name: `UniversityCampusManagementSystem`.
4. Create a `src` folder/package if required by your Eclipse setup.
5. Copy all `.java` files from the `src` folder into the project.
6. Open `Main.java`.
7. Right-click `Main.java`.
8. Select `Run As > Java Application`.
9. The console menu will appear.

## How to Run from Command Prompt

Open a terminal in the `src` directory:

```bash
javac *.java
java Main
```

## Demo Data

The application initially loads four student records for testing:

- 23DA2-0043 - E.A. Chamod Maleesha
- 23DA2-0439 - Nushrath Ahamed
- 23DA2-0261 - W.H.D.S. Fernando
- 23DA2-0346 - P.H.Yashodha Amashi

It also loads six sample campus locations and their connections.

## Suggested GitHub Commit Structure

Use separate meaningful commits, for example:

1. Initial project structure
2. Add Student class and Linked List
3. Implement Stack
4. Implement Service Queue
5. Implement Student BST
6. Implement Hash Table
7. Implement Campus Graph
8. Add BFS and DFS
9. Integrate main menu
10. Add input validation
11. Testing and bug fixes
12. Update README and final documentation

Each member should make commits that clearly show their own contribution. Branches and pull requests can be used for collaboration where applicable.

## Demonstration Checklist

The video should demonstrate:
- All group members' faces clearly visible as required by the assignment.
- Adding a student.
- Updating a student.
- Deleting a student.
- Linked-list display.
- Adding and processing service requests.
- Stack/action history.
- BST display.
- Hashing search.
- Adding/removing campus locations.
- Adding/removing campus roads.
- Campus adjacency list.
- BFS or DFS traversal.
- Invalid input handling.
- GitHub repository, commits and collaboration evidence.
- Individual contributions.

## Project Structure

```text
UniversityCampusManagementSystem/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentNode.java
│   ├── StudentLinkedList.java
│   ├── ActionStack.java
│   ├── ServiceRequest.java
│   ├── ServiceQueue.java
│   ├── BSTNode.java
│   ├── StudentBST.java
│   ├── StudentHashTable.java
│   ├── CampusGraph.java
│   └── InputValidator.java
└── README.md
```
