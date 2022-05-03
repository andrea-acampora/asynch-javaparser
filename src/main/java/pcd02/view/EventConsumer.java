package pcd02.view;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;


public class EventConsumer extends AbstractVerticle {

    private final View view;

    public EventConsumer(final View view) {
        this.view = view;
    }

    public void start(Promise<Void> startPromise) {
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("my-topic", view::notifyEvent);
        startPromise.complete();
    }
}
