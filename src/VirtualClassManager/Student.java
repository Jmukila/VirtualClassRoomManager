package VirtualClassManager;

public class Student implements Observer {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        System.out.println("Student " + name + " received notification: " + message);
    }
}
