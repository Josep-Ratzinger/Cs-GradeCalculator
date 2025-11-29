
import java.util.List;

/**
 * Política simple de puntos extra:
 * - Si la lista está vacía o todos están en false => 0 puntos extra.
 * - Si al menos un docente está de acuerdo (true) => +1 punto extra.
 *
 * Esto es un ejemplo coherente y determinista (RNF03).
 */
public class DefaultExtraPointsPolicy implements ExtraPointsPolicy {

    private static final double EXTRA_POINTS_VALUE = 1.0; // evita "número mágico"

    @Override
    public double calculateExtraPoints(double baseGrade, List<Boolean> allYearsTeachers) {
        if (allYearsTeachers == null || allYearsTeachers.isEmpty()) {
            return 0.0;
        }
        boolean anyAgree = allYearsTeachers.stream().anyMatch(Boolean::booleanValue);
        return anyAgree ? EXTRA_POINTS_VALUE : 0.0;
    }
}

