package processes;

import models.Mark;
import models.PlaceNumber;
import processes.manageApplications.Application;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdmissionVariables {
    private static AdmissionVariables instance;
    private Mark minimumMark;


    public static AdmissionVariables getInstance(){
        if (instance == null){
            instance = new AdmissionVariables();
        }
        return instance;
    }

    /*public AdmissionVariables() {
        placeNumber = new PlaceNumber(100);
        applications = new ArrayList<>();

        Application application1 = new Application();
        application1.setUsername("usernameflo1");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("NAME","florent");
        map1.put("MARK", "10");
        application1.setInformations(map1);

        applications.add(application1);

        Application application2 = new Application();
        application2.setUsername("usernameantho2");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("NAME","anthony");
        map2.put("MARK", "15");
        application2.setInformations(map2);

        applications.add(application2);

        minimumMark = new Mark(9);
    }
*/
    private PlaceNumber placeNumber;
    private List<Application> applications = new ArrayList<>();
    private List<Application> incompletes = new ArrayList<>();
    private List<Application> underMark = new ArrayList<>();
    private List<Application> eliminations = new ArrayList<>();

    public List<Application> getApplications() {
        return applications;
    }

    public PlaceNumber getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(PlaceNumber placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setMinimumMark(Mark minimumMark) {
        this.minimumMark = minimumMark;
    }

    public Mark getMinimumMark() {
        return minimumMark;
    }

    public List<Application> getUnderMark() {
        return underMark;
    }

    public List<Application> getEliminations() {
        return eliminations;
    }

    public List<Application> getIncompletes() {
        return incompletes;
    }
}
