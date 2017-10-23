import models.NumerusClausus;

public class AdmissionParameters {
    private static AdmissionParameters instance;

    public static AdmissionParameters getInstance(){
        if (instance == null){
            instance = new AdmissionParameters();
        }
        return instance;
    }



    private double firstMarkLine;
    private NumerusClausus places;

    public double getFirstMarkLine() {
        return firstMarkLine;
    }

    public void setFirstMarkLine(double firstMarkLine) {
        this.firstMarkLine = firstMarkLine;
    }

    public NumerusClausus getPlaces() {
        return places;
    }

    public void setPlaces(NumerusClausus places) {
        this.places = places;
    }
}
