package org.flowable;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.List;

public class AdmissionPolytech {

    private static ProcessEngine processEngine;

    public static void main(String[] args) {
        initializeEngine();
        deployResourceAsProcess("holiday-request.bpmn20.xml");
        /*while( choseRole() ) {
            switch (currentRole) {
                case MANAGER:  beAManager(); break;
                case EMPLOYEE: beAnEmployee(); break;
            }
        }*/
        metrics();
        ProcessEngines.destroy();

    }

    private static void initializeEngine() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = cfg.buildProcessEngine();
    }

    private static void deployResourceAsProcess(String resourceName) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource(resourceName)
                .deploy();
    }

    private static void metrics() {
        System.out.println("\n\n/**\n * System Metrics\n **/\n");
        HistoryService historyService = processEngine.getHistoryService();
        List<ProcessInstance> instances = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .list();

        for(ProcessInstance processInstance: instances) {
            System.out.println("Metrics for process instance" + processInstance.getId());
            List<HistoricActivityInstance> activities =
                    historyService.createHistoricActivityInstanceQuery()
                            .processInstanceId(processInstance.getId())
                            .orderByHistoricActivityInstanceEndTime().asc()
                            .list();
            for (HistoricActivityInstance activity : activities) {
                System.out.println(activity.getActivityId() + " took "
                        + activity.getDurationInMillis() + " milliseconds");
            }
            System.out.println("");
        }
    }

}
