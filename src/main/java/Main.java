import org.flowable.IFlowableProcess;
import org.flowable.engine.ProcessEngines;
import processes.ManageCandidates;

public class Main {

    private static final String PROCESS_DEFINITION_KEY = "admissionPolytech";
    private static final String RESOURCE_XML = "Traiter_les_dossiers.bpmn20.xml";

    public static void main(String[] args) {
        IFlowableProcess process = new ManageCandidates(RESOURCE_XML);
        process.execute();
        ProcessEngines.destroy();
    }

}
