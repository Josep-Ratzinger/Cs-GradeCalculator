

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // para usar punto decimal en double
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CS-GradeCalculator ===");
        System.out.print("Ingrese el código del estudiante: ");
        String studentId = scanner.nextLine().trim();

        StudentRecord record = new StudentRecord(studentId);

        // Registrar evaluaciones (RF01)
        System.out.print("¿Cuántas evaluaciones desea registrar? (máximo 10): ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        if (n < 0 || n > 10) {
            System.out.println("Número de evaluaciones inválido. Debe estar entre 0 y 10.");
            return;
        }

        double totalWeight = 0.0;
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Evaluación " + i + " ---");
            System.out.print("Nombre: ");
            String name = scanner.nextLine().trim();

            System.out.print("Nota obtenida (0-20): ");
            double score = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Peso en la nota final (porcentaje 0-100): ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            totalWeight += weight;

            try {
                Evaluation eval = new Evaluation(name, score, weight);
                record.addEvaluation(eval);
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error al registrar evaluación: " + ex.getMessage());
                return;
            }
        }

        if (Math.abs(totalWeight - 100.0) > 0.0001) {
            System.out.println("Advertencia: La suma de pesos es " + totalWeight +
                    "% (lo ideal es 100%).");
        }

        // Asistencia mínima (RF02)
        System.out.print("¿El estudiante alcanzó la asistencia mínima? (S/N): ");
        String asistenciaInput = scanner.nextLine().trim().toUpperCase();
        boolean hasMinimumAttendance = asistenciaInput.startsWith("S");
        record.setHasReachedMinimumClasses(hasMinimumAttendance);

        // Política de puntos extra allYearsTeachers (RF03)
        System.out.print("¿Cuántos años académicos se consideran en allYearsTeachers?: ");
        int years = Integer.parseInt(scanner.nextLine().trim());

        List<Boolean> allYearsTeachers = new ArrayList<>();
        for (int i = 1; i <= years; i++) {
            System.out.print("¿El docente del año " + i +
                    " está de acuerdo en otorgar puntos extra? (S/N): ");
            String agree = scanner.nextLine().trim().toUpperCase();
            allYearsTeachers.add(agree.startsWith("S"));
        }

        // Cálculo de la nota final (RF04, RF05)
        GradeCalculator calculator = new GradeCalculator(
                new DefaultAttendancePolicy(),
                new DefaultExtraPointsPolicy()
        );

        CalculationResult result = calculator.calculateFinalGrade(record, allYearsTeachers);

        // Mostrar detalle del cálculo (RF05)
        System.out.println("\n=== Resultado del cálculo ===");
        System.out.printf("Promedio ponderado: %.2f%n", result.getWeightedAverage());
        System.out.printf("Penalización por asistencia: %.2f%n", result.getAttendancePenalty());
        System.out.printf("Puntos extra aplicados: %.2f%n", result.getExtraPoints());
        System.out.printf("Nota final del estudiante: %.2f%n", result.getFinalGrade());

        scanner.close();
    }
}
