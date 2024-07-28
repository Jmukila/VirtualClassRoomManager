package VirtualClassManager;

public class NormalSubmissionStrategy implements AssignmentSubmissionStrategy {
    @Override
    public void submit(Assignment assignment, String feedback) {
        // Implementation for normal submission
        System.out.println("Normal submission: " + assignment + " with feedback: " + feedback);
    }
}
