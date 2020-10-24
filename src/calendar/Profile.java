import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Profile {
  // public as users can see each other's meetings
  // !!add to profile
  public HashMap<Date, ArrayList<Meeting>> calendar;

  // ignore below attributes
  public String name;
  private static Map<Long, Profile> users = new HashMap<>();  // static??

  public Profile(long id, String name) {
    this.name = name; // ignore
    this.calendar = new HashMap<Date, ArrayList<Meeting>>(); // need to add to actual profile
    users.put(id, this); //ignore
  }

  // need for BBcalendar methods
  // !!add to USER CLASS and change bbcalender
  public static Profile getUser(Long userID) {
    return users.get(userID);
  }

  public static void main(String[] args) {
    Profile player1 = new Profile(1, "ayesha");
    Profile player2 = new Profile(2, "manya");
    Profile[] list1 = {player1};
    Profile[] list2 = {player2};
    Long[] list3 = {1L, 2L};
    Date d1 = new Date(120, 9, 24, 11, 30);
    Date d2 = new Date(120, 9, 24, 12, 30);
    Date d3 = new Date(120, 9, 24, 13, 30);
    Date d4 = new Date(120, 9, 25, 13, 30);
    Date d5 = new Date(120, 9, 25, 14, 30);
    // month is 0 indexed

    // Method 1
    BBCalendar.addMeeting(list1, d1, d2, "p1 meeting");
    BBCalendar.addMeeting(list1, d4, d5, "p2 meeting");
    BBCalendar.addMeeting(list2, d2, d3, "p3 meeting");
    
    // Method 2
    BBCalendar.displayUsersDay(1L);
    BBCalendar.displayUsersDay(2L);

    // Method 3
    BBCalendar.displayUsersCalendarForGivenDay(1l, d4);

    // Method 4
    BBCalendar.meetingTimeSuggestion(1l, d1, "9:00", "15:00", 1);

    // Method 5
    BBCalendar.meetingTimeScheduler(list3, d1, "9:00", "15:00", 1);
  }

}
