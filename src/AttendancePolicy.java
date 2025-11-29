
/**
 * Define cómo afecta la asistencia a la nota del estudiante (RF02).
 */
public interface AttendancePolicy {

    /**
     * Devuelve la penalización a restar por asistencia.
     *
     * @param baseGrade                 promedio ponderado antes de asistencia
     * @param hasReachedMinimumClasses  true si cumplió asistencia mínima
     * @return cantidad de puntos a restar (>= 0)
     */
    double calculatePenalty(double baseGrade, boolean hasReachedMinimumClasses);
}
