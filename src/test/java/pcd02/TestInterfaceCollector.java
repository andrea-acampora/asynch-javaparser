package pcd02;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.junit.jupiter.api.Test;
import pcd02.interfaces.InterfaceReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInterfaceCollector {

    private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

    @Test
    public void testInterfaceCollector() throws FileNotFoundException {
        Future<InterfaceReport> fut = this.projectAnalyzer.getInterfaceReport("src/main/java/pcd02/interfaces/InterfaceReport.java");
        fut.onSuccess(res -> {
            assertEquals("src/main/java/pcd02/interfaces/InterfaceReport.java", res.getSrcFullFileName());
            assertEquals("InterfaceReport", res.getFullInterfaceName());
            assertEquals(6, res.getMethods().size());
        });
    }
}
