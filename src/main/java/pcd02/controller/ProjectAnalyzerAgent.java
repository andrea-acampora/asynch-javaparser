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
    public void start() {
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("stop", ev -> this.vertx.undeploy(this.deploymentID()));
        this.vertx.executeBlocking(promise -> {
            try {
                SourceRoot sourceRoot = new SourceRoot(Paths.get(folderToParse));
                sourceRoot.tryToParse();
                List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
                ProjectVisitor projectVisitor = new ProjectVisitor(getVertx(), topicAddress);
                compilationUnits.forEach(cu -> this.vertx.executeBlocking(prom -> projectVisitor.visit(cu, null)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
