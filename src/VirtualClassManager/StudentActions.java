package VirtualClassManager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

public class StudentActions {
    private ClassroomManager classroomManager;

    public StudentActions() {
        classroomManager = ClassroomManager.getInstance();
    }

    public void viewAssignments(String className) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            classroom.showAssignments();
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public void submitAssignment(String className, String studentId, String title, String content, Date submissionDate) throws ClassroomNotFoundException, StudentNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            boolean studentExists = classroom.getStudents().stream()
                    .anyMatch(student -> student.getId().equals(studentId));
            if (studentExists) {
                // Find the assignment to determine the due date
                Assignment assignment = classroom.getAssignments().stream()
                        .filter(a -> a.getTitle().equals(title))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Assignment " + title + " not found in classroom " + className + "."));
                
                // Determine the submission strategy
                AssignmentSubmissionStrategy strategy;
                if (submissionDate.after(assignment.getDueDate())) {
                    strategy = new LateSubmissionStrategy();
                } else {
                    strategy = new NormalSubmissionStrategy();
                }
                
                strategy.submit(assignment, content);
                Logger.log("Assignment " + title + " submitted by " + studentId + " to " + className + ".");
            } else {
                throw new StudentNotFoundException("Student " + studentId + " not found in classroom " + className + ".");
            }
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public void viewNotifications(String studentId) throws StudentNotFoundException {
        List<Classroom> classrooms = classroomManager.getAllClassrooms().stream()
                .filter(classroom -> classroom.getStudents().stream()
                        .anyMatch(student -> student.getId().equals(studentId)))
                .collect(Collectors.toList());

        if (classrooms.isEmpty()) {
            throw new StudentNotFoundException("Student " + studentId + " not found in any classroom.");
        }

        System.out.println("Select the classroom to view notifications for student " + studentId + ":");
        for (int i = 0; i < classrooms.size(); i++) {
            System.out.println((i + 1) + ". " + classrooms.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= classrooms.size()) {
            Classroom selectedClassroom = classrooms.get(choice - 1);
            selectedClassroom.showNotificationsForStudent(studentId);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
