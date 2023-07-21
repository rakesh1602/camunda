package com.crossasyst.camunda.listeners;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TaskListeners implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("Task listeners executed.");
    }
}
