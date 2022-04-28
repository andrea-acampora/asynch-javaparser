package pcd02;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pcd02.interfaces.PackageReport;
import pcd02.interfaces.ProjectReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(VertxExtension.class)
public class TestProjectCollector {

    private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

    @Test
    @DisplayName("Test project collector")
    public void testProjectCollector(VertxTestContext testContext) {
        Future<ProjectReport> fut = projectAnalyzer.getProjectReport("src/");
        fut.onSuccess(res -> testContext.verify(() -> {
            assertNotEquals(0, res.getAllPackages().size());
            assertTrue(res.getMainClass().isPresent());
            testContext.completeNow();
        }));
    }
}
