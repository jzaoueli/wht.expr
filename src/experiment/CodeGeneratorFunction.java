package experiment;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * set up and generate CSV file
 * to use this function :
 * create instance of CodeGeneratorFunction and call
 * - instance.setHeader()
 * - instance.addCycle()
 */
class CodeGeneratorFunction {

    private String workingString = "";
    private String path = "";

    /**
     * the path of the generated file will be created from the packageName
     *
     * @param subjectName : name of test person will be the name of generated file
     */
    CodeGeneratorFunction(String subjectName) {
        path = "data/" + subjectName + "-" + getTime() + ".csv";
        this.workingString = "cycleID;speed red (pxl/frame);speed blue(pxl/frame);userPerception\n";
    }

    void appendCycle(int cycleID, int modX, int modY, String userPerception) {
        //TODO get speed instead of modX and modY

        this.workingString += cycleID + ";" + (float) 1 / modX + ";" + (float) 1 / modY + ";" + userPerception + "\n";
    }

    /**
     * create and generate file with
     *
     * @throws IOException
     * @workingString must be modified before calling
     */
    void createAndWriteInFile() throws IOException {
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        writer.write(workingString);
        writer.flush();
        writer.close();
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH-mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

}
