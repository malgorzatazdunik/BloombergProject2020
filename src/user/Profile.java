package user;
//package Data;
import calendar.Meeting;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Profile {
  private long id;
  private String name;
  private String emailAddress;
  private String phoneNumber;
  private Map<Date, ArrayList<Meeting>> calendar = new HashMap<Date, ArrayList<Meeting>>();

  public Profile(long id, String name, String emailAddress, String phoneNumber) {
    this.id = id;
    this.name = name;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public long getId() {
    return id;
  }

  public Map<Date, ArrayList<Meeting>> getCalendar() {
    return calendar;
  }

}
