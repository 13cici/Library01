package Models;

import java.util.List;
import java.util.UUID;

public interface ProfessorRepository {
    Professor get(UUID id);
    void add(Professor professor);
    boolean update(Professor professor);
    void remove(Professor professor);
    List<Professor> getAllProfessors();
}
