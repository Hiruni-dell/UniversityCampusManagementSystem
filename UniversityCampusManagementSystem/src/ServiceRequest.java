public class ServiceRequest {
    private static int nextRequestNumber = 1;
    private final int requestNumber;
    private final String studentId;
    private final String description;

    public ServiceRequest(String studentId, String description) {
        this.requestNumber = nextRequestNumber++;
        this.studentId = studentId;
        this.description = description;
    }

    public int getRequestNumber() { return requestNumber; }
    public String getStudentId() { return studentId; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Request #" + requestNumber + " | Student ID: " + studentId +
               " | Request: " + description;
    }
}
