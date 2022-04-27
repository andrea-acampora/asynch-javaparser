package pcd02.lib;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.*;
import pcd02.interfaces.*;
import pcd02.reports.ClassReportImpl;
import pcd02.reports.InterfaceReportImpl;
import pcd02.visitors.ClassReportCollector;
import pcd02.visitors.InterfaceReportCollector;

import java.io.File;
import java.util.function.Consumer;

public class ProjectAnalyzerImpl implements ProjectAnalyzer {

    private final Vertx vertx;

    public ProjectAnalyzerImpl(final Vertx vertx ) {
        this.vertx = vertx;
    }

    @Override
    public Future<ClassReport> getClassReport(final String srcClassPath) {
        return this.vertx.executeBlocking(promise -> {
            try {
                ClassReportCollector collector = new ClassReportCollector();
                ClassReport report = new ClassReportImpl(srcClassPath);
                CompilationUnit cu = StaticJavaParser.parse(new File(srcClassPath));
                collector.visit(cu, report);
                promise.complete(report);
            } catch (Exception e) {
                e.printStackTrace();
                promise.fail(e);
            }
        });
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(final String srcInterfacePath) {
        return this.vertx.executeBlocking(promise -> {
            try {
                InterfaceReportCollector collector = new InterfaceReportCollector();
                InterfaceReport report = new InterfaceReportImpl(srcInterfacePath);
                CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
                collector.visit(cu, report);
                promise.complete(report);
            } catch (Exception e) {
                e.printStackTrace();
                promise.fail(e);
            }
        });
    }

    @Override
    public Future<PackageReport> getPackageReport(final String srcPackagePath) {
        return null;
    }

    @Override
    public Future<ProjectReport> getProjectReport(final String srcProjectFolderPath) {
        return null;
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, Consumer<ProjectElem> callback) {}
}
