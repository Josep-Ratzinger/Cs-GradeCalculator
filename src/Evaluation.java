
/**
 * Representa una evaluación del estudiante (RF01).
 */
public class Evaluation {

    private final String name;        // nombre de la evaluación
    private final double score;       // nota obtenida
    private final double weight;      // porcentaje de aporte a la nota final (0–100)

    public Evaluation(String name, double score, double weight) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la evaluación no puede ser vacío");
        }
        if (score < 0 || score > 20) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 20");
        }
        if (weight <= 0 || weight > 100) {
            throw new IllegalArgumentException("El peso debe estar entre 0 y 100");
        }
        this.name = name;
        this.score = score;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public double getWeight() {
        return weight;
    }
}

