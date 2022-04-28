package pcd02;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pcd02.interfaces.InterfaceReport;
import pcd02.interfaces.PackageReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(VertxExtension.class)
public class TestPackageCollector {

    private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

    @Test
    @DisplayName("Test package collector")
    public void testPackageCollector(VertxTestContext testContext) {
        Future<PackageReport> fut = projectAnalyzer.getPackageReport("src/main/java/pcd02/lib/");
        fut.onSuccess(res -> testContext.verify(() -> {
            assertEquals("src/main/java/pcd02/lib/", res.getFullPackagePath());
            assertEquals("pcd02.lib", res.getFullPackageName());
            assertEquals(1, res.getClassInfo().size());
            assertEquals(1, res.getInterfaceInfo().size());
            testContext.completeNow();
        }));
    }
}
