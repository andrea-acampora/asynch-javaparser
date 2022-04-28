package pcd02.app;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd02.interfaces.ClassReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

public class TestClassReport {

    public static void main(String[] args) throws FileNotFoundException {
        Vertx vertx = Vertx.vertx();
        ProjectAnalyzer lib = new ProjectAnalyzerImpl(vertx);
        Future<ClassReport> fut = lib.getClassReport("src/main/java/pcd02/lib/ProjectAnalyzerImpl.java");
        fut.onSuccess(res -> {
            System.out.println("Class name: " +  res.getFullClassName());
            System.out.println("Path: " +  res.getSrcFullFileName());
            System.out.println("Methods: " +  res.getMethodsInfo());
            System.out.println("Fields: " +  res.getFieldsInfo());
        });
        fut.onComplete(event -> System.exit(0));
    }
}
