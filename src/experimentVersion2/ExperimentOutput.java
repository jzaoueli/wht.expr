package experimentVersion2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Michael on 17.06.2016.
 */
public class ExperimentOutput {
    private String filename;
    private ArrayList<Integer> faster;
    private ArrayList<Float> speedRed, speedGreen;
    private int numberOfIterations, iteration;
    PrintWriter writer;

    public ExperimentOutput(String filename, int numberOfIterations) {
        this.filename = filename;
        this.numberOfIterations = numberOfIterations;
        this.speedRed = new ArrayList<>();
        this.speedGreen = new ArrayList<>();
        this.faster = new ArrayList<>();

    }

    public void writeHeader() {

        try {
            writer = new PrintWriter("data/" + filename, "UTF-8");

            writer.println("Iteration; SpeedX; SpeedY; Which is percieved faster?(0=red, 1=green, 2=same Speed)");


           /* for(int i=0;i<numberOfIterations; i++ ) {

            }*/
            //writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void addIteration(float SpeedX, float SpeedY, int Faster) {
/*speedRed.add(iteration, SpeedX);
        speedGreen.add(iteration, SpeedY);
        speedRed.add(iteration, SpeedX);
        faster.add(iteration, Faster);*/
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
