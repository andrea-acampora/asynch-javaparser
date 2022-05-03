package pcd02.controller;

import io.vertx.core.Vertx;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

public class Controller implements Observer {

    private final ProjectAnalyzer lib;

    public Controller(final Vertx vertx) {
        this.lib = new ProjectAnalyzerImpl(vertx);
    }

    @Override
    public void notifyStart(final String path) {
        this.lib.analyzeProject(path, "my-topic");
    }

    @Override
    public void notifyStop() {

    }
}
