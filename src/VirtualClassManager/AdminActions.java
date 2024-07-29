package VirtualClassManager;

import java.util.List;
import java.util.stream.Collectors;

public class AdminActions {
    private ClassroomManager classroomManager;
    private ClassroomFactory classroomFactory;

    public AdminActions() {
        classroomManager = ClassroomManager.getInstance();
        classroomFactory = new ClassroomFactory();
    }

    public void addClassroom(String type, String name) {
        if (classroomManager.getClassroom(name) != null) {
            Logger.log("Classroom " + name + " already exists.");
            System.out.println("Classroom " + name + " already exists.");
            return;
        }

        Classroom classroom = classroomFactory.createClassroom(type, name);
        classroomManager.addClassroom(classroom);
        Logger.log("Added " + type + " classroom: " + name);
    }

    public void removeClassroom(String name) {
        classroomManager.removeClassroom(name);
        Logger.log("Removed classroom: " + name);
    }

    public List<String> listClassrooms() {
        return classroomManager.getAllClassrooms().stream()
                .map(Classroom::getName)
                .collect(Collectors.toList());
    }

    public void addStudent(String studentId, String studentName, String className) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null ) {
            classroom.addStudent(new Student(studentId, studentName));
            Logger.log("Added student " + studentName + " to classroom " + className);
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public void removeStudent(String studentId, String className) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            classroom.removeStudent(studentId);
            Logger.log("Removed student " + studentId + " from classroom " + className);
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public List<Student> listStudents(String className) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            return classroom.getStudents();
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public void scheduleAssignment(String className, Assignment assignment) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            classroom.addAssignment(assignment);
            Logger.log("Scheduled assignment " + assignment.getTitle() + " for classroom " + className + ". Due date: " + assignment.getDueDate());
            System.out.println("Assignment scheduled successfully.");
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }


    public void notifyStudents(String className, String message) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            classroom.addNotification(message);
            Logger.log("Notified students in classroom " + className + ": " + message);
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }

    public void viewSubmissions(String className) throws ClassroomNotFoundException {
        Classroom classroom = classroomManager.getClassroom(className);
        if (classroom != null) {
            classroom.showAssignments();
        } else {
            throw new ClassroomNotFoundException("Classroom " + className + " not found.");
        }
    }
}
