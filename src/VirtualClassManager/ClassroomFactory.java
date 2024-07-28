package VirtualClassManager;

public class ClassroomFactory {
    public Classroom createClassroom(String type, String name) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularClassroom(name);
            case "q&a":
                return new QAClassroom(name);
            case "workshop":
                return new WorkshopClassroom(name);
            default:
                throw new IllegalArgumentException("Unknown classroom type: " + type);
        }
    }
}
