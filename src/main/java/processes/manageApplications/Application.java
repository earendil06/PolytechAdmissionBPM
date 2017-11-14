package processes.manageApplications;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Application implements Serializable {


    public double getMarkInterview() {
        return markInterview;
    }

    public double getTotal() {
        return getMarkInterview() * 0.2 + Double.parseDouble((String) getInformations().get(Data.MARK.toString())) * 0.8;
    }

    public void setMarkInterview(double markInterview) {
        this.markInterview = markInterview;
    }

    public enum Data { NAME, MARK, LINK_ID_CARD }

    private String username;
    private Map<String, Object> informations;

    private double markInterview;

    public Application(String username, Map<String, Object> informations) {
        this.username = username;
        this.informations = informations;
    }

    public Application() {
        this.informations = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getInformations() {
        return informations;
    }

    public void setInformations(Map<String, Object> informations) {
        this.informations = informations;
    }

    public boolean isComplete(){
        for (Data data : Data.values()) {
            if (!informations.keySet().contains(data.toString())){
                return false;
            }
        }
        return true;
    }
}
