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
        System.out.println("started");
        System.out.println("thread = " + Thread.currentThread().getName());
        vertx.executeBlocking(promise -> {
            try {
                System.out.println("thread = " + Thread.currentThread().getName());
                SourceRoot sourceRoot = new SourceRoot(Paths.get(folderToParse));
                long time = System.currentTimeMillis();
                sourceRoot.tryToParse();
                System.out.println("elapsed = " + (System.currentTimeMillis() - time));
                List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
                ProjectVisitor projectVisitor = new ProjectVisitor(getVertx(), topicAddress);
                compilationUnits.forEach(cu -> {
                    System.out.println("before visit cu");
                    this.vertx.executeBlocking(prom -> {
                        projectVisitor.visit(cu, null);
                        prom.complete();
                    });
                    System.out.println("after visit cu");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            promise.complete();
        });
        System.out.println("terminated");

    }

    @Override
    public void stop(){
        System.out.println("verticle stopped");
    }
}
