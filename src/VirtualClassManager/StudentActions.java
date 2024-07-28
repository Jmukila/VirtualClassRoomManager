package VirtualClassManager;

import java.util.Date; // Import statement for Date
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

    public void submitAssignment(String className, String studentId, String title, String content) throws ClassroomNotFoundException, StudentNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            boolean studentExists = classroom.getStudents().stream()
                    .anyMatch(student -> student.getId().equals(studentId));
            if (studentExists) {
                Assignment assignment = new Assignment(title, content, new Date()); // Ensure Date is correctly imported
                AssignmentSubmissionStrategy strategy = new NormalSubmissionStrategy(); // Assuming a normal submission for simplicity
                strategy.submit(assignment, content);
                Logger.log("Assignment " + assignment.getTitle() + " submitted by " + studentId + " to " + className + ".");
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
