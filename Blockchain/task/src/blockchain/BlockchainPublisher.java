package blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockchainPublisher {
    private List<Subscriber> subscribers;

    public BlockchainPublisher() {
        subscribers = new ArrayList<>();
    }

    public void alert() {
        subscribers.forEach(Subscriber::update);
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }
}
