package VirtualClassManager;

public class QAClassroom extends Classroom {
    public QAClassroom(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Q&A";
    }
}
