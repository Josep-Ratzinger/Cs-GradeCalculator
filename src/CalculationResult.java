
/**
 * Resultado detallado del cálculo de la nota (RF05).
 */
public class CalculationResult {

    private final double weightedAverage;   // promedio ponderado
    private final double attendancePenalty;// puntos restados por asistencia
    private final double extraPoints;       // puntos extra aplicados
    private final double finalGrade;        // nota final

    public CalculationResult(double weightedAverage,
                             double attendancePenalty,
                             double extraPoints,
                             double finalGrade) {
        this.weightedAverage = weightedAverage;
        this.attendancePenalty = attendancePenalty;
        this.extraPoints = extraPoints;
        this.finalGrade = finalGrade;
    }

    public double getWeightedAverage() {
        return weightedAverage;
    }

    public double getAttendancePenalty() {
        return attendancePenalty;
    }

    public double getExtraPoints() {
        return extraPoints;
    }

    public double getFinalGrade() {
        return finalGrade;
    }
}

