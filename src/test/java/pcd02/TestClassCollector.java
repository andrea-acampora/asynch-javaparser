package pcd02;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pcd02.interfaces.ClassReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

@ExtendWith(VertxExtension.class)
class TestClassCollector {

    private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

    @Test
    @DisplayName("Test class collector")
    public void testClassCollector(VertxTestContext testContext) throws FileNotFoundException {
        Future<ClassReport> fut = projectAnalyzer.getClassReport("src/main/java/pcd02/lib/ProjectAnalyzerImpl.java");
        fut.onSuccess(res -> testContext.verify(() -> {
            assertEquals("src/main/java/pcd02/lib/ProjectAnalyzerImpl.java", res.getSrcFullFileName());
            assertEquals("ProjectAnalyzerImpl", res.getFullClassName());
            testContext.completeNow();
        }));
    }
}
