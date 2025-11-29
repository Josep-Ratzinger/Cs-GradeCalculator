
import java.util.List;

/**
 * Encapsula el cálculo de la nota final del estudiante (RF04).
 */
public class GradeCalculator {

    private final AttendancePolicy attendancePolicy;
    private final ExtraPointsPolicy extraPointsPolicy;

    public GradeCalculator(AttendancePolicy attendancePolicy,
                           ExtraPointsPolicy extraPointsPolicy) {
        if (attendancePolicy == null || extraPointsPolicy == null) {
            throw new IllegalArgumentException("Las políticas no pueden ser null");
        }
        this.attendancePolicy = attendancePolicy;
        this.extraPointsPolicy = extraPointsPolicy;
    }

    /**
     * Calcula la nota final y devuelve el detalle (RF04, RF05).
     */
    public CalculationResult calculateFinalGrade(StudentRecord record,
                                                 List<Boolean> allYearsTeachers) {
        if (record == null) {
            throw new IllegalArgumentException("El registro del estudiante no puede ser null");
        }

        // 1. Promedio ponderado de evaluaciones
        double weightedAverage = record.calculateWeightedAverage();

        // 2. Penalización por asistencia
        double penalty = attendancePolicy.calculatePenalty(
                weightedAverage,
                record.hasReachedMinimumClasses()
        );
        double afterAttendance = weightedAverage - penalty;

        // Evitar negativos por redondeo
        if (afterAttendance < 0) {
            afterAttendance = 0;
        }

        // 3. Puntos extra
        double extraPoints = extraPointsPolicy.calculateExtraPoints(afterAttendance, allYearsTeachers);

        // 4. Nota final = después de asistencia + extra
        double finalGrade = afterAttendance + extraPoints;

        // Limitar entre 0 y 20 por seguridad
        if (finalGrade < 0) {
            finalGrade = 0;
        } else if (finalGrade > 20) {
            finalGrade = 20;
        }

        return new CalculationResult(weightedAverage, penalty, extraPoints, finalGrade);
    }
}
