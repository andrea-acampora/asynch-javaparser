package pcd02;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.InterfaceReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

public class TestClassCollector {

    public static void main(String[] args) throws FileNotFoundException {
        ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl();
        Future<ClassReport> fut = projectAnalyzer.getClassReport("src/main/java/pcd02/reports/ClassReportImpl.java");
        fut.onComplete((AsyncResult<ClassReport> res) -> {
            System.out.println("Source file name: " + res.result().getSrcFullFileName());
            System.out.println("Class name: " + res.result().getFullClassName());
            System.out.println("Methods: " + res.result().getMethodsInfo());
            System.out.println("Fields: " + res.result().getFieldsInfo());
        });
    }

}
