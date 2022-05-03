package pcd02.app;

import io.vertx.core.Vertx;
import pcd02.controller.Controller;
import pcd02.view.EventConsumer;
import pcd02.view.View;

public class Main {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        Controller controller = new Controller(vertx);
        View view = new View();
        view.addObserver(controller);
        vertx.deployVerticle(new EventConsumer(view), res -> view.start());
    }
}
