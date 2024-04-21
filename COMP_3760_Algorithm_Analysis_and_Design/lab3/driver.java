import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class driver {
    public static void main(String[] args) {
        HashSimulator hs = new HashSimulator();

        String fileName = "5608names.txt";
        int arraySize = 5608;
        String[] namesArray = new String[arraySize];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < arraySize) {
                namesArray[index] = line;
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] results = hs.runHashSimulation(namesArray, 560800);
        System.out.println("H1: " + results[0] + " " + results[1]);
        System.out.println("H2: " + results[2] + " " + results[3]);
        System.out.println("H3: " + results[4] + " " + results[5]);

    }
}
