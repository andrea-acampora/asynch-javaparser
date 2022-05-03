package pcd02.tests;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd02.interfaces.PackageReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;


public class TestPackageReport {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ProjectAnalyzer lib = new ProjectAnalyzerImpl(vertx);
        Future<PackageReport> fut = lib.getPackageReport("src/main/java/pcd02/");
        fut.onSuccess(res -> {
            System.out.println("Package name: " +  res.getFullPackageName());
            System.out.println("Path: " +  res.getFullPackagePath());
            System.out.println("Num classes: " +  res.getClassInfo().size());
            System.out.println("Num interfaces: " +  res.getInterfaceInfo().size());
        });
        fut.onComplete(event -> System.exit(0));
    }
}
