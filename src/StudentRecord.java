
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registro de notas y asistencia de un estudiante.
 */
public class StudentRecord {

    private final String studentId;           // código o identificador del estudiante
    private final List<Evaluation> exams;     // evaluations (RF01)
    private boolean hasReachedMinimumClasses; // asistencia mínima (RF02)

    private static final int MAX_EVALUATIONS = 10; // RNF01

    public StudentRecord(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("El código del estudiante no puede ser vacío");
        }
        this.studentId = studentId;
        this.exams = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean hasReachedMinimumClasses() {
        return hasReachedMinimumClasses;
    }

    public void setHasReachedMinimumClasses(boolean hasReachedMinimumClasses) {
        this.hasReachedMinimumClasses = hasReachedMinimumClasses;
    }

    public List<Evaluation> getExams() {
        return Collections.unmodifiableList(exams);
    }

    /**
     * Registra una evaluación (RF01) respetando el máximo de RNF01.
     */
    public void addEvaluation(Evaluation evaluation) {
        if (evaluation == null) {
            throw new IllegalArgumentException("La evaluación no puede ser null");
        }
        if (exams.size() >= MAX_EVALUATIONS) {
            throw new IllegalStateException("Se alcanzó el máximo de evaluaciones permitidas (10)");
        }
        exams.add(evaluation);
    }

    /**
     * Calcula el promedio ponderado de todas las evaluaciones.
     */
    public double calculateWeightedAverage() {
        double result = 0.0;
        for (Evaluation e : exams) {
            result += e.getScore() * (e.getWeight() / 100.0);
        }
        return result;
    }
}
