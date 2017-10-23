package processes;

import org.flowable.AbstractProcess;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

public class ManageCandidates extends AbstractProcess {

    private String key = "manageCandidates";

    public ManageCandidates(String xmlFile) {
        super(xmlFile);
    }

    public void execute() {
        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        Map<String, Object> map = new HashMap<>();
        ProcessInstance inst = runtimeService.startProcessInstanceByKey(key, map);
    }
}
