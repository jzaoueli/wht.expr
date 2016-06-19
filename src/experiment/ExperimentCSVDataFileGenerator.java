package experiment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * generate the experiment data in csv file
 */
public class ExperimentCSVDataFileGenerator {
    private String filename;
    private ArrayList<Integer> faster;
    private ArrayList<Float> speedRed, speedBlue;
    private int numberOfIterations, iteration;
    PrintWriter writer;

    public ExperimentCSVDataFileGenerator(String filename, int numberOfIterations) {
        this.filename = filename;
        this.numberOfIterations = numberOfIterations;
        this.speedRed = new ArrayList<>();
        this.speedBlue = new ArrayList<>();
        this.faster = new ArrayList<>();

    }

    public void writeHeader() {
        try {
            writer = new PrintWriter("data/" + filename, "UTF-8");
            writer.println("Iteration; SpeedX; SpeedY; faster perceived (0=R, 1=B, 2=same)");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addIteration(float SpeedX, float SpeedY, int Faster) {
        iteration++;
        writer.println(iteration + ";" + SpeedX + ";" + SpeedY + ";" + Faster);
    }

    public void close() {
        writer.close();
    }

    public void setFilename(String fileName) {
        this.filename = fileName;
        this.writeHeader();
    }
}
