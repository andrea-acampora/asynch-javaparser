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
        log("visiting compilation unit");
        this.vertx.executeBlocking(promise -> super.visit(cu, collector));
        log("after visiting compilation unit");
    }

    // PACKAGE DECLARATION VISIT USING EXECUTE_BLOCKING AND PROJECT ELEM
    public void visit(PackageDeclaration pd, Void collector) {
        log("visiting package declaration");
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
        log("after visiting package declaration");
    }

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Void collector) {
        super.visit(classOrInterfaceDeclaration, collector);
        ProjectElem projectElem = new ProjectElemImpl();
        if (classOrInterfaceDeclaration.isInterface()) {
            projectElem.setType(ElemType.INTERFACE);
            this.vertx.eventBus().publish(topicAddress, projectElem.getType().toString() );
        } else {
            projectElem.setType(ElemType.CLASS);
            this.vertx.eventBus().publish(topicAddress, projectElem.getType().toString());
        }
    }

    public void visit(MethodDeclaration md, Void collector) {
        super.visit(md, collector);
        ProjectElem projectElem = new ProjectElemImpl();
        projectElem.setType(ElemType.METHOD);
        this.vertx.eventBus().publish(topicAddress, projectElem.getType().toString());
    }

    public void visit(FieldDeclaration fd, Void collector) {
        super.visit(fd, collector);
        ProjectElem projectElem = new ProjectElemImpl();
        projectElem.setType(ElemType.FIELD);
        this.vertx.eventBus().publish(topicAddress, projectElem.getType().toString());
    }

    private void log(String message) {
        System.out.println("[ Thread: " + Thread.currentThread().getName() + " ]" + ": " + message);
    }
}
