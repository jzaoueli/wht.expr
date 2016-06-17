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
    private ArrayList<Integer> speedRed, speedGreen, faster;
    private int numberOfIterations;

    public ExperimentOutput(String filename, int numberOfIterations) {
        this.filename = filename;
        this.numberOfIterations=numberOfIterations;
        this.speedRed =new ArrayList<>();
        this.speedGreen = new ArrayList<>();
        this.faster =new ArrayList<>();

    }

    public void writeToFile(){

        try (PrintWriter writer = new PrintWriter("data/"+filename, "UTF-8")) {

                writer.println("Iteration, SpeedX, SpeedY, Which is percieved faster?(0=red, 1=green, 2=same Speed)");


            for(int i=0;i<numberOfIterations; i++ ) {
                writer.println(i + ", " + speedRed.get(i) + ", " + speedGreen.get(i) + ", " + faster.get(i));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void addIteration(int iteration, int SpeedX, int SpeedY,int Faster){
speedRed.add(iteration, SpeedX);
        speedGreen.add(iteration, SpeedY);
        speedRed.add(iteration, SpeedX);
        faster.add(iteration, Faster);

    }



}
