
/**
 * Política simple:
 * - Si NO alcanzó la asistencia mínima, la nota final queda en 0.
 *   => penalización = baseGrade
 * - Si sí alcanzó la asistencia mínima, no se resta nada.
 */
public class DefaultAttendancePolicy implements AttendancePolicy {

    @Override
    public double calculatePenalty(double baseGrade, boolean hasReachedMinimumClasses) {
        if (hasReachedMinimumClasses) {
            return 0.0;
        }
        return baseGrade; // lo deja en 0
    }
}

