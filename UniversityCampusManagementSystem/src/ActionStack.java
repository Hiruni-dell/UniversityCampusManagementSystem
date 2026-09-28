import java.util.ArrayList;
import java.util.List;

public class ActionStack {
    private final List<String> stack = new ArrayList<>();

    public void push(String action) {
        stack.add(action);
    }

    public String pop() {
        if (isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        System.out.println("\n--- Recent Actions (Stack: top to bottom) ---");
        if (stack.isEmpty()) {
            System.out.println("No actions recorded.");
            return;
        }

        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println((stack.size() - i) + ". " + stack.get(i));
        }
    }
}
