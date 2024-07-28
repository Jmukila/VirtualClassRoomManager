package VirtualClassManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassroomManager {
    private static ClassroomManager instance;
    private Map<String, Classroom> classrooms;

    private ClassroomManager() {
        classrooms = new HashMap<>();
    }

    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }

    public void addClassroom(Classroom classroom) {
        classrooms.put(classroom.getName(), classroom);
    }

    public Classroom getClassroom(String name) {
        return classrooms.get(name);
    }

    public List<Classroom> getAllClassrooms() {
        return classrooms.values().stream().collect(Collectors.toList());
    }

    public void removeClassroom(String name) {
        classrooms.remove(name);
    }

    public boolean classroomExists(String name) {
        return classrooms.containsKey(name);
    }
}
