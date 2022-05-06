package pcd02.view;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;

public class EventConsumerAgent extends AbstractVerticle {

    private final View view;
    public EventConsumerAgent(final View view) {
        this.view = view;
    }

    public void start(Promise<Void> startPromise) {
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("stop", ev -> this.vertx.undeploy(this.deploymentID()));
        eb.consumer("my-topic", view::notifyEvent);
        startPromise.complete();
    }

    @Override
    public void stop() {
        log("EventConsumerAgent stopped");
    }

    private void log(String message) {
        System.out.println("[ Thread: " + Thread.currentThread().getName() + " ]" + ": " + message);
    }
}
