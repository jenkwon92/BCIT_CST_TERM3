import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JobAssignmentFinder finder = new JobAssignmentFinder();
        ArrayList<String> list = new ArrayList<>();
        list.add("data0.txt");
        list.add("data1.txt");
        list.add("data2.txt");
        list.add("data3.txt");
        list.add("data4.txt");
        list.add("data5.txt");
        list.add("data11.txt");
        list.add("data12.txt");
        list.add("data37.txt");
        list.add("data148.txt");

        try {
            for (String i : list) {
                finder.readDataFile(i);
                System.out.println("result of " + i);
                // System.out.println("Benefit Matrix:");
                // System.out.println(finder.benefitMatrixToString());

                System.out.println("Greedy Assignment:");
                System.out.println(finder.getGreedyAssignment());

                System.out.println("Total value of Greedy Assignment:");
                System.out.println(finder.greedyAssignmentTotalValue());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}