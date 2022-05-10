package pcd02.view;

import io.vertx.core.eventbus.Message;
import pcd02.controller.Observer;
import pcd02.interfaces.ProjectElem;
import pcd02.reports.ProjectElemImpl;

import java.lang.reflect.InvocationTargetException;


public class View {

    private final ProjectAnalyzerGUI gui;

    public View() {
        this.gui = new ProjectAnalyzerGUI();
    }

    public void addObserver(Observer observer) {
        this.gui.addObserver(observer);
    }

    public void start() {
        this.gui.start();
    }

    public void notifyEvent(Message<Object> message){
        this.gui.notifyEvent(message);
    }
}