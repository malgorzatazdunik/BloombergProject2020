package Data;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.util.Pair;

public class Security {
  private long id;
  private String industry;
  private String description;
  private List<String> listOfBoardMemebers;
  private Set<Pair<Timestamp, Double>> prices = new TreeSet<>(Comparator.comparing(Pair::getKey));
  private double lowestPrice = -1;
  private double highestPrice = -1;

  public Security(long id, String industry, String description,
      List<String> listOfBoardMemebers) {
    this.id = id;
    this.industry = industry;
    this.description = description;
    this.listOfBoardMemebers = listOfBoardMemebers;
  }

  public double getLowestPrice() {
    return lowestPrice;
  }

  public double getHighestPrice() {
    return highestPrice;
  }

  public void insertPrice(Timestamp timestamp, double price) {
    if(lowestPrice == -1 || lowestPrice > price) lowestPrice = price;
    if(highestPrice == -1 || highestPrice < price) highestPrice = price;
    prices.add(new Pair<>(timestamp, price));
  }

  public void getPriceHistory(Timestamp startTime, Timestamp endTime) {
    for(Pair<Timestamp, Double> price : prices) {
      if(price.getKey().compareTo(endTime) > 0) return;
      if(price.getKey().compareTo(startTime) < 0) continue;
      System.out.println("price at " + price.getKey() + " : " + price.getValue());
    }
  }
}
