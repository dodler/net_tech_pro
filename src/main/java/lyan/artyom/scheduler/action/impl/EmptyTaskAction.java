package lyan.artyom.scheduler.action.impl;

import lyan.artyom.scheduler.action.TaskAction;

/**
 * Created by dodler on 28/02/16.
 */
public class EmptyTaskAction implements TaskAction {
    @Override
    public void perform() {
        System.out.println("Empty action performed");
    }
}