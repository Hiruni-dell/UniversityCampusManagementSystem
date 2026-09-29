import java.util.*;

public class CampusGraph {
    private final Map<String, LinkedHashSet<String>> adjacencyList =
            new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) return false;
        String existing = findLocationKey(location);
        if (existing != null) return false;
        adjacencyList.put(location.trim(), new LinkedHashSet<>());
        return true;
    }

    public boolean removeLocation(String location) {
        String key = findLocationKey(location);
        if (key == null) return false;

        adjacencyList.remove(key);
        for (Set<String> neighbours : adjacencyList.values()) {
            neighbours.removeIf(n -> n.equalsIgnoreCase(key));
        }
        return true;
    }

    public boolean addConnection(String from, String to) {
        String fromKey = findLocationKey(from);
        String toKey = findLocationKey(to);

        if (fromKey == null || toKey == null || fromKey.equalsIgnoreCase(toKey)) return false;

        boolean addedFrom = adjacencyList.get(fromKey).add(toKey);
        boolean addedTo = adjacencyList.get(toKey).add(fromKey);
        return addedFrom && addedTo;
    }

    public boolean removeConnection(String from, String to) {
        String fromKey = findLocationKey(from);
        String toKey = findLocationKey(to);

        if (fromKey == null || toKey == null) return false;

        boolean removed1 = adjacencyList.get(fromKey).removeIf(n -> n.equalsIgnoreCase(toKey));
        boolean removed2 = adjacencyList.get(toKey).removeIf(n -> n.equalsIgnoreCase(fromKey));
        return removed1 || removed2;
    }

    public int getLocationCount() {
        return adjacencyList.size();
    }

    public int getConnectionCount() {
        int totalDegree = 0;
        for (Set<String> neighbours : adjacencyList.values()) {
            totalDegree += neighbours.size();
        }
        return totalDegree / 2;
    }

    public void displayConnections() {
        System.out.println("\n--- Campus Network (Adjacency List) ---");
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found.");
            return;
        }

        for (Map.Entry<String, LinkedHashSet<String>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            if (entry.getValue().isEmpty()) {
                System.out.println("No direct connections");
            } else {
                System.out.println(String.join(", ", entry.getValue()));
            }
        }

        System.out.println("Locations: " + getLocationCount() +
                " | Connections: " + getConnectionCount());
    }

    public void bfs(String start) {
        String startKey = findLocationKey(start);
        if (startKey == null) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(startKey);
        visited.add(startKey);

        System.out.println("\n--- BFS Traversal ---");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.println(current);

            for (String neighbour : adjacencyList.get(current)) {
                if (!containsIgnoreCase(visited, neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
    }

    public void dfs(String start) {
        String startKey = findLocationKey(start);
        if (startKey == null) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();
        System.out.println("\n--- DFS Traversal ---");
        dfsRecursive(startKey, visited);
    }

    private void dfsRecursive(String current, Set<String> visited) {
        visited.add(current);
        System.out.println(current);

        for (String neighbour : adjacencyList.get(current)) {
            if (!containsIgnoreCase(visited, neighbour)) {
                dfsRecursive(neighbour, visited);
            }
        }
    }

    private boolean containsIgnoreCase(Set<String> set, String value) {
        for (String item : set) {
            if (item.equalsIgnoreCase(value)) return true;
        }
        return false;
    }

    private String findLocationKey(String location) {
        if (location == null) return null;
        for (String key : adjacencyList.keySet()) {
            if (key.equalsIgnoreCase(location.trim())) return key;
        }
        return null;
    }

    public boolean containsLocation(String location) {
        return findLocationKey(location) != null;
    }
}