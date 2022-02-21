package Models;

import java.util.List;
import java.util.UUID;

public interface StudentRepository {
    Student get(UUID id);
    void add(Student student);
    boolean update(Student student);
    void remove(Student student);
    List<Student> getAllStudents();
}
