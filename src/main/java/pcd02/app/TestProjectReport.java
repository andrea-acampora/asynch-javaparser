package pcd02.app;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd02.interfaces.ProjectReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

public class TestProjectReport {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ProjectAnalyzer lib = new ProjectAnalyzerImpl(vertx);
        Future<ProjectReport> fut = lib.getProjectReport("src/");
        fut.onSuccess(res -> {
            System.out.println("Packages: " + res.getAllPackages());
            System.out.println("Main class: " + res.getMainClass());
        });
        fut.onComplete(event -> System.exit(0));
    }
}
