// Purpose: Driver for JobAssignmentFinder

public class driver {
    public static void main(String[] args) {
        JobAssignmentFinder jaf = new JobAssignmentFinder();
        jaf.readDataFile("data.txt");
        for (int i = 0; i < 6; i++) {
            jaf.readDataFile("data" + i + ".txt");
            // System.out.println(jaf.getInputSize());
            // System.out.println(jaf.getBenefitMatrix());
            // System.out.println(jaf.benefitMatrixToString());
            System.out.println(jaf.getMaxAssignment());
            System.out.println(jaf.getMaxAssignmentTotalValue());
            // System.out.println(jaf.getBenefit(0, 0));
        }
    }
}
