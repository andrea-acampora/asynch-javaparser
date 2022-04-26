package pcd02;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import pcd02.interfaces.InterfaceReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

public class TestInterfaceCollector {

    public static void main(String[] args) throws FileNotFoundException {
        ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl();
        Future<InterfaceReport> fut = projectAnalyzer.getInterfaceReport("src/main/java/pcd02/interfaces/InterfaceReport.java");
        fut.onComplete((AsyncResult<InterfaceReport> res) -> {
            System.out.println("Source file name: " + res.result().getSrcFullFileName());
            System.out.println("Interface name: " + res.result().getFullInterfaceName());
            System.out.println("Methods: " + res.result().getMethods());
        });
    }

}
