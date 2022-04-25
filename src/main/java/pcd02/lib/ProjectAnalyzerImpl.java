package pcd02.lib;

import io.vertx.core.Future;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.PackageReport;
import pcd02.interfaces.ProjectElem;
import pcd02.interfaces.ProjectReport;

import java.util.function.Consumer;

public class ProjectAnalyzerImpl implements ProjectAnalyzer{

    @Override
    public Future<ClassReport> getClassReport(String srcClassPath) {
        return null;
    }

    @Override
    public Future<ClassReport> getInterfaceReport(String srcInterfacePath) {
        return null;
    }

    @Override
    public Future<PackageReport> getPackageReport(String srcPackagePath) {
        return null;
    }

    @Override
    public Future<ProjectReport> getProjectReport(String srcProjectFolderPath) {
        return null;
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, Consumer<ProjectElem> callback) {

    }
}
