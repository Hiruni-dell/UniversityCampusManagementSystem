public class StudentHashTable {
    private static class Entry {
        String key;
        Student value;
        Entry next;

        Entry(String key, Student value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Entry[] table;

    public StudentHashTable(int capacity) {
        table = new Entry[capacity];
    }

    private int hash(String key) {
        return Math.floorMod(key.toLowerCase().hashCode(), table.length);
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) table[i] = null;
    }

    public void put(Student student) {
        String key = student.getStudentId();
        int index = hash(key);

        Entry current = table[index];
        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                current.value = student;
                return;
            }
            current = current.next;
        }

        Entry entry = new Entry(key, student);
        entry.next = table[index];
        table[index] = entry;
    }

    public Student get(String key) {
        int index = hash(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    public boolean remove(String key) {
        int index = hash(key);
        Entry current = table[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                if (previous == null) table[index] = current.next;
                else previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void display() {
        System.out.println("\n--- Hash Table ---");
        boolean empty = true;

        for (int i = 0; i < table.length; i++) {
            Entry current = table[i];
            if (current != null) {
                empty = false;
                System.out.print("Bucket " + i + ": ");
                while (current != null) {
                    System.out.print("[" + current.key + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }

        if (empty) System.out.println("Hash table is empty.");
    }
}
