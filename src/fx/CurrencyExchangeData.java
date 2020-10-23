package fx;

class CurrencyExchangeData {
    private String from;
    private String to;
    private Double price;
    private Integer timestamp;

    public CurrencyExchangeData(String from, String to, Double price, Integer timestamp) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

	public Integer getTimestamp() {
		return timestamp;
	}
}
