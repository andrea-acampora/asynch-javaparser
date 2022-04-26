package pcd02;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.PackageReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

public class TestPackageCollector {

    public static void main(String[] args) throws FileNotFoundException {
        ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl();
        Future<PackageReport> fut = projectAnalyzer.getPackageReport("pcd02.visitors");
        fut.onComplete((AsyncResult<PackageReport> res) -> {
            System.out.println("package name: " + res.result().getFullPackageName());
            System.out.println("Classes: " + res.result().getClassInfo());
            System.out.println("Interfaces: " + res.result().getInterfaceInfo());
            System.out.println("Main class: " + res.result().getMainClass());
        });
    }
}
