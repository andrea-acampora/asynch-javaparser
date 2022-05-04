package pcd02.controller;

import io.vertx.core.Vertx;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

public class Controller implements Observer {

    private final ProjectAnalyzer lib;
    private final Vertx vertx;

    public Controller(final Vertx vertx) {
        this.vertx = vertx;
        this.lib = new ProjectAnalyzerImpl(vertx);
    }

    @Override
    public void notifyStart(final String path) {
        this.lib.analyzeProject(path, "my-topic");
    }

    @Override
    public void notifyStop() {
        this.vertx.eventBus().publish("stop", "stop");
    }
}
