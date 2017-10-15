package org.flowable;

import java.util.Scanner;

public class AdmissionPolytech extends AbstractProcess {

    private Scanner scanner;
    private String processKey;

    public AdmissionPolytech(String xmlFile, String processKey) {
        super(xmlFile);
        this.processKey = processKey;
        this.scanner = new Scanner(System.in);
    }

    private void definirPlaces() {
        System.out.println("Quelle est la note minimale ?");
        int nb = Integer.parseInt(scanner.nextLine());
    }


    public void execute() {
        definirPlaces();
    }
}
