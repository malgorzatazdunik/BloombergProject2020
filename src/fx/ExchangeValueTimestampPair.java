package fx;

class ExchangeValueTimestampPair {
    private Double value;
    private Integer timestamp;

    public ExchangeValueTimestampPair(Double value, Integer timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public Double getValue() {
        return value;
    }
}

