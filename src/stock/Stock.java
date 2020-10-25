package stock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
  private Map<Long, Security> securities = new HashMap<>();

  public void addBasicSecurityInfo(long securityID, String industry, String description,
      List<String> listOfBoardMembers) {
    Security security = new Security(securityID, industry, description, listOfBoardMembers);
    securities.put(securityID, security);
  }

  public void addTick(long securityID, double price, Timestamp timestamp) {
    securities.get(securityID).insertPrice(timestamp, price);
  }

  public double allTimeHigh(long securityID) {
    return securities.get(securityID).getHighestPrice();
  }

  public double allTimeLow(long securityID) {
    return securities.get(securityID).getLowestPrice();
  }

  public void getAllPriceHistory(long securityID, Timestamp startTime, Timestamp endTime) {
    securities.get(securityID).getPriceHistory(startTime, endTime);
  }

  public static void main(String[] args) {
    Stock stock = new Stock();
    stock.addBasicSecurityInfo(321, "tech", "desc", new ArrayList<>());
    stock.addBasicSecurityInfo(123, "tech", "desc", new ArrayList<>());
    stock.addTick(123, 23.94, new Timestamp(123));
    stock.addTick(123, 50.12, new Timestamp(234));
    stock.addTick(123, 10.23, new Timestamp(592));
    stock.addTick(123, 30.12, new Timestamp(999));
    System.out.println(stock.allTimeHigh(123));
    System.out.println(stock.allTimeLow(321));
    stock.getAllPriceHistory(123, new Timestamp(200), new Timestamp(800));
  }
}
