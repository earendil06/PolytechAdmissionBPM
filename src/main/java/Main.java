import org.flowable.AdmissionPolytech;
import org.flowable.IFlowableProcess;
import org.flowable.engine.ProcessEngines;

public class Main {

    private static final String PROCESS_DEFINITION_KEY = "admissionPolytech";
    private static final String RESOURCE_XML = "admission-polytech.bpmn20.xml";

    public static void main(String[] args) {
        IFlowableProcess process = new AdmissionPolytech(RESOURCE_XML, PROCESS_DEFINITION_KEY);
        process.execute();
        ProcessEngines.destroy();
    }

}
