import models.NumerusClausus;
import org.flowable.AbstractProcess;

import java.util.Scanner;

public class AdmissionPolytech extends AbstractProcess {

    private Scanner scanner;
    private String processKey;

    public AdmissionPolytech(String xmlFile, String processKey) {
        super(xmlFile);
        this.processKey = processKey;
        this.scanner = new Scanner(System.in);
    }

    private void definePlaces() {
        NumerusClausus numerus = new NumerusClausus(100);
        AdmissionParameters.getInstance().setPlaces(numerus);
    }


    public void execute() {
        definePlaces();

    }
}
