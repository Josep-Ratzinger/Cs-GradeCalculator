
import java.util.List;

/**
 * Define cómo se calculan los puntos extra (RF03).
 */
public interface ExtraPointsPolicy {

    /**
     * Calcula los puntos extra a partir de la política allYearsTeachers.
     *
     * @param baseGrade        nota después de aplicar asistencia
     * @param allYearsTeachers lista de acuerdos de docentes (True/False)
     * @return puntos extra a sumar
     */
    double calculateExtraPoints(double baseGrade, List<Boolean> allYearsTeachers);
}
