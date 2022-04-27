package pcd02;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.junit.jupiter.api.Test;
import pcd02.interfaces.ClassReport;
import pcd02.lib.ProjectAnalyzer;
import pcd02.lib.ProjectAnalyzerImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

public class TestClassCollector {

        private final ProjectAnalyzer projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());

        @Test
        public void testClassCollector() throws FileNotFoundException {
            Future<ClassReport> fut = projectAnalyzer.getClassReport("src/main/java/pcd02/reports/ClassReportImpl.java");
            fut.onSuccess(res -> {
                assertEquals("src/main/java/pcd02/reports/ClassReportImpl.java", res.getSrcFullFileName());
                assertEquals("ClassReportImpl", res.getFullClassName());
                assertEquals(4, res.getFieldsInfo().size());
            });
        }
}
