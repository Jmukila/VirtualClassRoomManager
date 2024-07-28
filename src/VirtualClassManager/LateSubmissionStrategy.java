package VirtualClassManager;

public class LateSubmissionStrategy implements AssignmentSubmissionStrategy {
    @Override
    public void submit(Assignment assignment, String feedback) {
        // Implementation for late submission
        System.out.println("Late submission: " + assignment + " with feedback: " + feedback);
    }
}
