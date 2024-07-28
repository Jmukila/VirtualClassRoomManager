package VirtualClassManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Classroom implements Subject {
    private String name;
    private List<Student> students;
    private List<Assignment> assignments;
    private List<String> notifications;
    private List<Observer> observers = new ArrayList<>();

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void addNotification(String notification) {
        notifications.add(notification);
        notifyObservers(notification);
        System.out.println("Notification: " + notification);
    }

    public void addStudent(Student student) {
        for (Student existingStudent : students) {
            if (existingStudent.getId().equals(student.getId())) {
                System.out.println("Student with ID " + student.getId() + " already exists.");
                return;
            }
        }
        students.add(student);
        addObserver(student);
        System.out.println("Student " + student.getName() + " has been added to classroom " + name + ".");
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
        System.out.println("Assignment " + assignment.getTitle() + " has been added to classroom " + name + ".");
    }

    public void showAssignments() {
        if (assignments.isEmpty()) {
            System.out.println("No assignments for this classroom.");
        } else {
            assignments.forEach(assignment -> System.out.println(assignment.getTitle() + ": " + assignment.getDescription() + " (Due: " + assignment.getDueDate() + ")"));
        }
    }

    public void showNotificationsForStudent(String studentId) {
        if (notifications.isEmpty()) {
            System.out.println("No notifications for student " + studentId);
        } else {
            System.out.println("Notifications for student " + studentId + ":");
            notifications.forEach(notification -> System.out.println(notification));
        }
    }

    public void showStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in classroom " + name + ".");
        } else {
            System.out.println("Students in classroom " + name + ":");
            for (Student student : students) {
                System.out.println(student.getId() + ": " + student.getName());
            }
        }
    }

    public void removeStudent(String studentId) {
        boolean removed = students.removeIf(student -> student.getId().equals(studentId));
        if (removed) {
            System.out.println("Student with ID " + studentId + " has been removed from classroom " + name + ".");
        } else {
            System.out.println("No student with ID " + studentId + " found in classroom " + name + ".");
        }
    }

    public void submitAssignment(String studentId, String assignmentTitle, String submissionContent) {
        Optional<Assignment> assignmentOpt = assignments.stream().filter(a -> a.getTitle().equals(assignmentTitle)).findFirst();
        if (assignmentOpt.isPresent()) {
            Assignment assignment = assignmentOpt.get();
            assignment.setSubmissionContent(submissionContent);
            System.out.println("Assignment submitted by student " + studentId + " for " + assignmentTitle + ".");
        } else {
            System.out.println("Assignment " + assignmentTitle + " not found in classroom " + name + ".");
        }
    }

    public String getType() {
        return "Generic";
    }
}
