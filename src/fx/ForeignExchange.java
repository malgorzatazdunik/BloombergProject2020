package fx;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
import java.util.*; 


class ForeignExchange {
    private final static Integer TEN = 10;
    private final static Integer MAX_CAPACITY = 100;

    private PriorityQueue<CurrencyExchangeData> currencyByTimestamp = new PriorityQueue<CurrencyExchangeData>(MAX_CAPACITY,  new CurrencyExchangeDataComparator());
    private CurrencyGraph currencyGraph = new CurrencyGraph();

    public void addForeignExchangeValue(String fromCurrency, String toCurrency, Double price, Integer timestamp) {
        currencyByTimestamp.add(new CurrencyExchangeData(fromCurrency, toCurrency, price, timestamp)); 
        currencyGraph.addExchangeValue(fromCurrency, toCurrency, price, timestamp);
    }

    public void displayTop10RecentPrices() {
        PriorityQueue<CurrencyExchangeData> copy = new PriorityQueue<>(currencyByTimestamp);
        int index = 0;

        while (index < TEN && !copy.isEmpty()) {
            CurrencyExchangeData data = copy.poll();
            System.out.println(data.getPrice());

            index++;
        }
    }

    public void displayTopMostRecentPricesForCurrencyPair(String fromCurrency, String toCurrency, int numberOfPrices) {
        PriorityQueue<CurrencyExchangeData> copy = new PriorityQueue<>(currencyByTimestamp);
        int pricesPosition = 0;

        while (!copy.isEmpty() && pricesPosition < numberOfPrices) {
            CurrencyExchangeData data = copy.poll();
           
            if (isSameExchange(data, fromCurrency, toCurrency)) {
                System.out.println(data.getPrice());
                pricesPosition++;
            }
        }
    }

    public void displayBestPriceToBuyCurrency(String fromCurrency, String toCurrency) {
        Set<String> nodes = currencyGraph.getNodes();
        Map<String, Double> distances = nodes.stream().collect(Collectors.toMap(elem -> elem, elem -> Double.MAX_VALUE));
        Set<String> visited = new HashSet<>();
        
        distances.put(fromCurrency, 0d);

        for (int i = 0; i < nodes.size(); i++) {
            String minDistanceVertex = "";
            Double minDistance = Double.MAX_VALUE;
            for (Map.Entry<String, Double> data : distances.entrySet()) {
                if (!visited.contains(data.getKey()) && data.getValue() < minDistance) {
                    minDistance = data.getValue();
                    minDistanceVertex = data.getKey();
                }
            }

            visited.add(minDistanceVertex);
            
            for (Map.Entry<String, ExchangeValueTimestampPair> node : currencyGraph.getNeighbours(minDistanceVertex).entrySet()) {
                String nodeName = node.getKey();
                ExchangeValueTimestampPair pair = node.getValue(); 

                if (distances.getOrDefault(minDistanceVertex, Double.MAX_VALUE) + pair.getValue() < distances.getOrDefault(nodeName, Double.MAX_VALUE)) {
                    distances.put(nodeName, distances.get(minDistanceVertex) + pair.getValue());
                }
            }
        }
        
        System.out.println(distances.get(toCurrency));
    }

    private boolean isSameExchange(CurrencyExchangeData data, String from, String to) {
        return data.getFrom().equals(from) && data.getTo().equals(to);
    }
}