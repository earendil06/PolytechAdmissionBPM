package processes.minimumMark;

import org.flowable.AbstractProcess;
import processes.AdmissionVariables;

public class MinimumMarkProcess extends AbstractProcess {


    private String key = "minimumMark";
    private static final String RESOURCE_XML = "Definir_note_minimale.bpmn20.xml";

    public MinimumMarkProcess() {
        super(RESOURCE_XML);
    }

    @Override
    public boolean isCompleted() {
        return AdmissionVariables.getInstance().getMinimumMark() != null;
    }

    @Override
    public void process() {
        getProcessEngine().getRuntimeService().startProcessInstanceByKey(key);
    }
}
