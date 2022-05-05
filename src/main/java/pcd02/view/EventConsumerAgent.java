package pcd02.view;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;


public class EventConsumerAgent extends AbstractVerticle {

    private final View view;
    private MessageConsumer<Object> consumer;

    public EventConsumerAgent(final View view) {
        this.view = view;
    }

    public void start(Promise<Void> startPromise) {
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("stop", ev -> stop());
        this.consumer = eb.consumer("my-topic", view::notifyEvent);
        startPromise.complete();
    }

    @Override
    public void stop(){
        this.consumer.unregister();
        log("EventConsumerAgent stopped");
    }

    private void log(String message) {
        System.out.println("[ Thread: " + Thread.currentThread().getName() + " ]" + ": " + message);
    }
}
