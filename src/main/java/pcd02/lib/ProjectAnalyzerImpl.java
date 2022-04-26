package pcd02.lib;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import pcd02.interfaces.*;
import pcd02.reports.ClassReportImpl;
import pcd02.reports.InterfaceReportImpl;
import pcd02.reports.PackageReportImpl;
import pcd02.reports.ProjectReportImpl;
import pcd02.visitors.ClassReportCollector;
import pcd02.visitors.InterfaceReportCollector;
import pcd02.visitors.PackageReportCollector;
import pcd02.visitors.ProjectReportCollector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class ProjectAnalyzerImpl implements ProjectAnalyzer {

    @Override
    public Future<ClassReport> getClassReport(final String srcClassPath) throws FileNotFoundException {
        Promise<ClassReport> promise = Promise.promise();
        ClassReportCollector collector = new ClassReportCollector();
        ClassReport report = new ClassReportImpl(srcClassPath);
        CompilationUnit cu = StaticJavaParser.parse(new File(srcClassPath));
        collector.visit(cu, report);
        promise.complete(report);
        return promise.future();
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(final String srcInterfacePath) throws FileNotFoundException {
        Promise<InterfaceReport> promise = Promise.promise();
        InterfaceReportCollector collector = new InterfaceReportCollector();
        InterfaceReport report = new InterfaceReportImpl(srcInterfacePath);
        CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
        collector.visit(cu, report);
        promise.complete(report);
        return promise.future();
    }

    @Override
    public Future<PackageReport> getPackageReport(final String srcPackagePath) throws FileNotFoundException {
        Promise<PackageReport> promise = Promise.promise();
        PackageReportCollector collector = new PackageReportCollector();
        PackageReport report = new PackageReportImpl();
        CompilationUnit cu = StaticJavaParser.parse(new File(srcPackagePath));
        collector.visit(cu, report);
        promise.complete(report);
        return promise.future();
    }

    @Override
    public Future<ProjectReport> getProjectReport(final String srcProjectFolderPath) throws FileNotFoundException {
        Promise<ProjectReport> promise = Promise.promise();
        ProjectReportCollector collector = new ProjectReportCollector();
        ProjectReport report = new ProjectReportImpl();
        CompilationUnit cu = StaticJavaParser.parse(new File(srcProjectFolderPath));
        collector.visit(cu, report);
        promise.complete(report);
        return promise.future();
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, Consumer<ProjectElem> callback) {}
}
