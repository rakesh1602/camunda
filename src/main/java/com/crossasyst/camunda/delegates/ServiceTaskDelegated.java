package com.crossasyst.camunda.delegates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ServiceTaskDelegated implements JavaDelegate {

    private Expression url;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Service Task executed.");
        log.info("url=>" + url.getValue(delegateExecution));

        log.info("Extension properties implementation.");
        ServiceTask serviceTask = (ServiceTask) delegateExecution.getBpmnModelElementInstance();
        ExtensionElements extensionElements = serviceTask.getExtensionElements();

        if (extensionElements != null) {
            List<CamundaProperty> camundaProperties = extensionElements.getElementsQuery()
                    .filterByType(CamundaProperty.class)
                    .list();

            for (CamundaProperty camundaProperty : camundaProperties) {
                log.info("Name =>" + camundaProperty.getCamundaName());
                log.info("Value =>" + camundaProperty.getCamundaValue());
            }
        }
    }
}
