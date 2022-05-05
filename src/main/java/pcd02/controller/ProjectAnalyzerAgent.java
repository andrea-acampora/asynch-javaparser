package pcd02.controller;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import pcd02.visitors.ProjectVisitor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class ProjectAnalyzerAgent extends AbstractVerticle {

    private final String folderToParse;
    private final String topicAddress;

    public ProjectAnalyzerAgent(String folderToParse, String topicAddress) {
        this.folderToParse = folderToParse;
        this.topicAddress = topicAddress;
    }

    @Override
    public void start(){
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("stop", ev -> stop());
            try {
                log("projectAnalyzer started");
                SourceRoot sourceRoot = new SourceRoot(Paths.get(folderToParse));
                sourceRoot.tryToParse();
                List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
                ProjectVisitor projectVisitor = new ProjectVisitor(getVertx(), topicAddress);
                compilationUnits.forEach(cu -> this.vertx.executeBlocking(promise -> projectVisitor.visit(cu, null)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        log("projectAnalyzer finished");
    }

    @Override
    public void stop() {
        log("ProjectAnalyzerAgent stopped");
    }

    private void log(String message) {
        System.out.println("[ Thread: " + Thread.currentThread().getName() + " ]" + ": " + message);
    }
}
