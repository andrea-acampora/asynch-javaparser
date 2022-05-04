package pcd02.lib;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import io.vertx.core.*;
import pcd02.controller.ProjectAnalyzerAgent;
import pcd02.interfaces.*;
import pcd02.reports.ClassReportImpl;
import pcd02.reports.InterfaceReportImpl;
import pcd02.reports.PackageReportImpl;
import pcd02.reports.ProjectReportImpl;
import pcd02.visitors.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

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
                ClassReport classReport = new ClassReportImpl();
                CompilationUnit cu = StaticJavaParser.parse(new File(srcClassPath));
                classReport.setSrcFullFileName(srcClassPath);
                collector.visit(cu, classReport);
                promise.complete(classReport);
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
                InterfaceReport interfaceReport = new InterfaceReportImpl();
                CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
                interfaceReport.setSrcFullFileName(srcInterfacePath);
                collector.visit(cu, interfaceReport);
                promise.complete(interfaceReport);
            } catch (Exception e) {
                e.printStackTrace();
                promise.fail(e);
            }
        });
    }

    @Override
    public Future<PackageReport> getPackageReport(final String srcPackagePath) {
        return this.vertx.executeBlocking(promise -> {
            try {
                SourceRoot sourceRoot = new SourceRoot(Paths.get(srcPackagePath));
                sourceRoot.tryToParse();
                List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
                PackageReport packageReport = new PackageReportImpl();
                packageReport.setPackagePath(srcPackagePath);
                packageReport.setFullPackageName(compilationUnits.size() > 0 && compilationUnits.get(0).getPackageDeclaration().isPresent() ?
                        compilationUnits.get(0).getPackageDeclaration().get().getNameAsString() : srcPackagePath);
                PackageReportCollector packageReportCollector = new PackageReportCollector();
                compilationUnits.forEach(cu -> packageReportCollector.visit(cu, packageReport));
                packageReportCollector.searchMainClass(packageReport);
                promise.complete(packageReport);
            } catch (Exception e) {
                e.printStackTrace();
                promise.fail(e);
            }
        });
    }

    @Override
    public Future<ProjectReport> getProjectReport(final String srcProjectFolderPath) {
        return this.vertx.executeBlocking(promise -> {
            try {
                SourceRoot sourceRoot = new SourceRoot(Paths.get(srcProjectFolderPath));
                sourceRoot.tryToParse();
                List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
                ProjectReport projectReport = new ProjectReportImpl();
                ProjectReportCollector projectReportCollector = new ProjectReportCollector();
                compilationUnits.forEach(cu -> projectReportCollector.visit(cu, projectReport));
                promise.complete(projectReport);
            } catch (Exception e) {
                e.printStackTrace();
                promise.fail(e);
            }
        });
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, String topicAddress) {
        this.vertx.deployVerticle(new ProjectAnalyzerAgent(srcProjectFolderName, topicAddress));
    }
}
