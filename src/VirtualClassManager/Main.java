package VirtualClassManager;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger.log("Starting Virtual Classroom Manager");
        Scanner scanner = new Scanner(System.in);
        AdminActions adminActions = new AdminActions();
        StudentActions studentActions = new StudentActions();

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int userType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userType) {
                case 1:
                    adminMenu(adminActions, scanner);
                    break;
                case 2:
                    studentMenu(studentActions, scanner);
                    break;
                case 3:
                    Logger.log("Exiting Virtual Classroom Manager");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu(AdminActions adminActions, Scanner scanner) {
        System.out.println("1. Add Classroom");
        System.out.println("2. Remove Classroom");
        System.out.println("3. List Classrooms");
        System.out.println("4. Add Student");
        System.out.println("5. Remove Student");
        System.out.println("6. List Students");
        System.out.println("7. Schedule Assignment");
        System.out.println("8. Notify Students");
        System.out.println("9. View Submissions");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter classroom type (Regular/Q&A/Workshop): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    String name = scanner.nextLine();
                    adminActions.addClassroom(type, name);
                    break;
                case 2:
                    System.out.print("Enter classroom name: ");
                    String classNameToRemove = scanner.nextLine();
                    adminActions.removeClassroom(classNameToRemove);
                    break;
                case 3:
                    List<String> classrooms = adminActions.listClassrooms();
                    for (String className : classrooms) {
                        System.out.println(className);
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    String className = scanner.nextLine();
                    adminActions.addStudent(studentId, studentName, className);
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    String studentIdToRemove = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    String classNameForRemoval = scanner.nextLine();
                    adminActions.removeStudent(studentIdToRemove, classNameForRemoval);
                    break;
                case 6:
                    System.out.print("Enter classroom name: ");
                    String classNameToListStudents = scanner.nextLine();
                    List<Student> students = adminActions.listStudents(classNameToListStudents);
                    for (Student student : students) {
                        System.out.println(student.getId() + ": " + student.getName());
                    }
                    break;
                case 7:
                    System.out.print("Enter classroom name: ");
                    String classNameToSchedule = scanner.nextLine();
                    System.out.print("Enter assignment title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter assignment description: ");
                    String description = scanner.nextLine();
                    Assignment assignment = new Assignment(title, description, new Date());
                    adminActions.scheduleAssignment(classNameToSchedule, assignment);
                    break;
                case 8:
                    System.out.print("Enter classroom name: ");
                    String classNameToNotify = scanner.nextLine();
                    System.out.print("Enter notification message: ");
                    String message = scanner.nextLine();
                    adminActions.notifyStudents(classNameToNotify, message);
                    break;
                case 9:
                    System.out.print("Enter classroom name: ");
                    String classNameToViewSubmissions = scanner.nextLine();
                    adminActions.viewSubmissions(classNameToViewSubmissions);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (ClassroomNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void studentMenu(StudentActions studentActions, Scanner scanner) {
        System.out.println("1. View Assignments");
        System.out.println("2. Submit Assignment");
        System.out.println("3. View Notifications");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter classroom name: ");
                    String className = scanner.nextLine();
                    studentActions.viewAssignments(className);
                    break;
                case 2:
                    System.out.print("Enter classroom name: ");
                    String classNameToSubmit = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter assignment title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter submission content: ");
                    String content = scanner.nextLine();
                    studentActions.submitAssignment(classNameToSubmit, studentId, title, content);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentIdToNotify = scanner.nextLine();
                    studentActions.viewNotifications(studentIdToNotify);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (ClassroomNotFoundException | StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
