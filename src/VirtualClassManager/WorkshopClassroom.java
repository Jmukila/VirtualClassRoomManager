package VirtualClassManager;

public class WorkshopClassroom extends Classroom {
    public WorkshopClassroom(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Workshop";
    }
}
