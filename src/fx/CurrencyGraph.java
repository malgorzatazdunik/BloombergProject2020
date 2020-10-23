package fx;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class CurrencyGraph {
    Map<String, Map<String, ExchangeValueTimestampPair>> nodes = new HashMap<>();

    public void addExchangeValue(String from, String to, Double price, Integer timestamp) {
        nodes.putIfAbsent(from, new HashMap<>());
        nodes.get(from).putIfAbsent(to, new ExchangeValueTimestampPair(price, timestamp));

        if (nodes.get(from).get(to).getTimestamp() < timestamp) {
            nodes.get(from).put(to, new ExchangeValueTimestampPair(price, timestamp));
        }
    }

    public Map<String, ExchangeValueTimestampPair> getNeighbours(String node) {
        return nodes.get(node);
    }

    public Set<String> getNodes() {
        return nodes.keySet();
    }
}
