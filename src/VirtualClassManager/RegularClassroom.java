package VirtualClassManager;

public class RegularClassroom extends Classroom {
    public RegularClassroom(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Regular";
    }
}
