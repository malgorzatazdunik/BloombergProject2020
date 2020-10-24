import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;


public class BBCalendar {

  // Helper methods

  // If date has a time, it resets the time to 00:00
  public static Date getPlainDate(Date fullDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {
      return sdf.parse(sdf.format(fullDate));
    } catch (Exception e) {
      return fullDate;
    }
  }

  // Maps date time to string time, 24 hour format
  public static String getPlainTime(Date fullDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    return sdf.format(fullDate);
  }

  // Maps string time to number of minutes
  public static int parseTime(String time) {
    int hour;
    int min;
    if (time.length() == 5) {
      hour = (Integer.parseInt(time.substring(0, 2)))*60;
      min = (Integer.parseInt(time.substring(3, 5)));
    } else {
      hour = (Integer.parseInt(time.substring(0, 1)))*60;
      min = (Integer.parseInt(time.substring(2, 4)));
    }
    return hour + min;
  }

  // Maps number of minutes to string time
  public static String unparseTime(int time) {
    int hour = time / 60;
    int minutes = time % 60;
    if (minutes == 0) {
      return hour + ":00";
    }
    return hour + ":" + minutes;
  }

  // Returns a set of minutes to represent slots that are full
  public static HashSet<Integer> getFilledSlots(ArrayList<Meeting> meetings) {
    HashSet<Integer> times = new HashSet<Integer>();

    for (Meeting m: meetings) {
      Integer start = parseTime(getPlainTime(m.getStartTime()));
      Integer end = parseTime(getPlainTime(m.getEndTime()));

      while (start < end) {
        times.add(start);
        start += 30;
      }
    }

    return times;
  }


  // Actual methods:

  /* 1) add a meeting to all users calendars
  from listOfUsers with given startTime, endTime and topic. */
  public static void addMeeting(Profile[] listOfUsers, Date startTime, Date endTime, String topic) {
    for (Profile user: listOfUsers) {
      Meeting meeting = new Meeting(startTime, endTime, topic);
      Date day = meeting.getCalendarDay();
      ArrayList<Meeting> allMeetings;
      
      if (user.calendar.containsKey(day)) {
        allMeetings = user.calendar.get(day);
        allMeetings.add(meeting);
        Collections.sort(allMeetings, new MeetingComparator());
      } else {
        allMeetings = new ArrayList<Meeting>();
        allMeetings.add(meeting);
      }

      user.calendar.put(day, allMeetings);
    }
  }

  /* 2) displays all the meetings the user has for a current day in a nice
  organized way (from earliest meeting to the latest). Make sure to display
  the time and topic of each meeting.*/
  public static void displayUsersDay(Long userID) {
    Date today = getPlainDate(new Date());
    displayUsersCalendarForGivenDay(userID, today);
    // calls method below, but gives today's date
  }

  /* 3) displays all the meetings the user has for a given day in a nice
  organized way (from earliest meeting to the latest).*/
  public static void displayUsersCalendarForGivenDay(Long userID, Date calendarDay) {
    Profile user = Profile.getUser(userID);
    calendarDay = getPlainDate(calendarDay);
    // In case the date given has a set time, we reset it
    ArrayList<Meeting> allMeetings = user.calendar.get(calendarDay);

    if (allMeetings != null) {
      System.out.println("Meetings Today:");
      for (Meeting meeting: allMeetings) {
        System.out.println("Starting Time: " + getPlainTime(meeting.getStartTime()));
        System.out.println("End Time: " + getPlainTime(meeting.getEndTime()));
        System.out.println("Topic: " + meeting.getTopic() + "\n");
      }
    } else {
      System.out.println("No meetings today.\n");
    }

  }

  

  /* 4) a method which prints out all available time slots for a user to put a
  meeting in. It has to start at or after earliestTime and finish at or before
  latestTime. Moreover, it should take the amount of time given in timeInterval.
  */
  public static void meetingTimeSuggestion(Long organisingUser, Date calendarDay, String earliestTime, String latestTime, int timeInterval) {
    // Uses method 5, the same way method 2 uses method 3
    meetingTimeScheduler(new Long[]{organisingUser}, calendarDay, earliestTime, latestTime, timeInterval);

  }

  /* 5) similar method as meetingTimeSuggestion, but instead of checking only one user
availability, you should check all users in the given list and print out available times only if a
time slot is available for all of the users. */
  public static void meetingTimeScheduler(Long[] listOfuserID, Date calendarDay, String earliestTime, String latestTime, int timeInterval) {
    // Assumptions:
    // Time interval = number of hours so half an hour = 0.5, an hour = 1, etc.
    // Assume times are strings in 24 hour format e.g. 9:00, 14:30
    

    // Earliest and latest time that we can start a meeting, in minutes
    Integer start = parseTime(earliestTime);
    Integer end = parseTime(latestTime) - (timeInterval*60);

    Profile user;
    ArrayList<Meeting> allMeetings;
    HashSet<Integer> filledSlots = new HashSet<Integer>();

    for (Long id: listOfuserID) {
      user = Profile.getUser(id);
      calendarDay = getPlainDate(calendarDay);
      // In case the date given has a set time, we reset it
      allMeetings = user.calendar.get(calendarDay);
      filledSlots.addAll(getFilledSlots(allMeetings));
    }
    // Creates an union of the filled slots for all members of the list
    // A slot that conflicts with any single members' schedule will be in the set
    


    int slotsToCheck;
    int freeSlots = 0;
    boolean noSlotClash;

    // Stores a copy of start, that we use to check following slots, so we don't lose the original value of start
    int tempStart;
    

    // while start <= the final time possible to start a meeting
    while (start <= end) {
      noSlotClash = true;
      tempStart = start;

      
      // the number of slots we need to check
      slotsToCheck = timeInterval * 2;
      while (slotsToCheck > 0) {
        if (filledSlots.contains(tempStart)) {
          noSlotClash = false;
          break;
        } else {
          tempStart += 30;
        }
        slotsToCheck -= 1;
      }

      if (noSlotClash) {
        System.out.println(unparseTime(start) + " - " + unparseTime(start+(timeInterval*60)));
        start += 30;
        freeSlots += 1;
        // output as the full slot was empty
        // increment to the next slot after the start of the full slot just checked
        // e.g. if 9-10 was empty, we now check 9.30 as 9.30-10.30 could also be empty
      } else {
        start = tempStart + 30;
        // as the current slot (tempStart) is full, we start the next iteration at the next slot
        // e.g. while checking 9-10, 10 was full, we next check 10.30
        // (not 9.30 as it wouldnt be possible if 9 was not possible)
      }
    }

    // if no slots were found
    if (freeSlots == 0) {
      System.out.println("No slots available.");
    }
    System.out.println();
  }


}
