package pcd02.visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd02.interfaces.ProjectElem;
import pcd02.reports.ElemType;
import pcd02.reports.ProjectElemImpl;

import java.util.HashSet;
import java.util.Set;

public class ProjectVisitor extends VoidVisitorAdapter<Void> {

    private final Vertx vertx;
    private final String topicAddress;
    private final Set<String> packages;

    public ProjectVisitor(Vertx vertx, String topicAddress) {
        this.vertx = vertx;
        this.topicAddress = topicAddress;
        this.packages = new HashSet<>();
    }

    public void visit(CompilationUnit cu, Void collector) {
        this.vertx.executeBlocking(promise -> super.visit(cu, collector));
    }

    public void visit(PackageDeclaration pd, Void collector) {
        Future<ProjectElem> fut = this.vertx.executeBlocking(promise -> {
            super.visit(pd, collector);
            ProjectElem projectElem = new ProjectElemImpl();
            projectElem.setType(ElemType.PACKAGE);
            promise.complete(projectElem);
        });
        fut.onSuccess(res -> {
            if (this.packages.add(pd.getNameAsString())) {
                this.vertx.eventBus().publish(topicAddress, res.getType().toString());
            }
        });
    }

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Void collector) {
        Future<ProjectElem> fut = this.vertx.executeBlocking(promise -> {
            super.visit(classOrInterfaceDeclaration, collector);
            ProjectElem projectElem = new ProjectElemImpl();
            if (classOrInterfaceDeclaration.isInterface()) {
                projectElem.setType(ElemType.INTERFACE);
            } else {
                projectElem.setType(ElemType.CLASS);
            }
            promise.complete(projectElem);
        });
        fut.onSuccess(res -> this.vertx.eventBus().publish(topicAddress, res.getType().toString()));
    }

    public void visit(MethodDeclaration md, Void collector) {
        Future<ProjectElem> fut = this.vertx.executeBlocking(promise -> {
            super.visit(md, collector);
            ProjectElem projectElem = new ProjectElemImpl();
            projectElem.setType(ElemType.METHOD);
            promise.complete(projectElem);
        });
        fut.onSuccess(res -> this.vertx.eventBus().publish(topicAddress, res.getType().toString()));

    }

    public void visit(FieldDeclaration fd, Void collector) {
        Future<ProjectElem> fut = this.vertx.executeBlocking(promise -> {
            super.visit(fd, collector);
            ProjectElem projectElem = new ProjectElemImpl();
            projectElem.setType(ElemType.FIELD);
            promise.complete(projectElem);
        });
        fut.onSuccess(res -> this.vertx.eventBus().publish(topicAddress, res.getType().toString()));
    }
}
