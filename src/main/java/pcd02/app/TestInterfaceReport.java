package pcd02.app;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd02.interfaces.InterfaceReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

public class TestInterfaceReport {

    public static void main(String[] args) throws FileNotFoundException {
        Vertx vertx = Vertx.vertx();
        ProjectAnalyzer lib = new ProjectAnalyzerImpl(vertx);
        Future<InterfaceReport> fut = lib.getInterfaceReport("src/main/java/pcd02/lib/ProjectAnalyzer.java");
        fut.onSuccess(res -> {
            System.out.println("Interface name: " +  res.getFullInterfaceName());
            System.out.println("Path: " +  res.getSrcFullFileName());
            System.out.println("Methods: " +  res.getMethods());
        });
        fut.onComplete(event -> System.exit(0));
    }
}
