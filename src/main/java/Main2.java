public class Main2 {

    public static void main(String[] args) {
        final double[] servingTimes = {0.003, 0.001, 0.01, 0.04, 0.1, 0.13, 0.15};
        final double[] visitCounts = {1, 2.5, 0.7713, 0.9043, 1.25, 1.5, 1};

        ResidenceTimeCalculator calculator =
                new ResidenceTimeCalculator(servingTimes, visitCounts);

        for (int i = 1; i <= 20; i++) {
            double lambda = (i / 10.0);

            calculator.calculate(lambda);
            System.out.println("lambda=" + lambda);

            printResidenceTime(calculator);
        }
    }

    private static void printResidenceTime(ResidenceTimeCalculator calculator) {

        for (int i = 1; i <= 7; i++) {
            System.out.println("T" + i + "=" + calculator.getResidenceTime(i));
        }

        System.out.println("T=" + calculator.getTotalResidenceTime());
    }

}
