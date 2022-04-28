package pcd02;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pcd02.interfaces.InterfaceReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(VertxExtension.class)
public class TestInterfaceCollector {

    private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

    @Test
    @DisplayName("Test interface collector")
    public void testInterfaceCollector(VertxTestContext testContext) throws FileNotFoundException {
        Future<InterfaceReport> fut = projectAnalyzer.getInterfaceReport("src/main/java/pcd02/lib/ProjectAnalyzer.java");
        fut.onSuccess(res -> testContext.verify(() -> {
            assertEquals("src/main/java/pcd02/lib/ProjectAnalyzer.java", res.getSrcFullFileName());
            assertEquals("ProjectAnalyzer", res.getFullInterfaceName());
            assertEquals(5, res.getMethods().size());
            testContext.completeNow();
        }));
    }
}
