package calendar;

import java.util.Date;

public class Meeting {
  private Date startTime;
  private Date endTime;
  private Date calendarDay;
  private String topic;
  
  public Meeting(Date start, Date end, String topic) {
    this.startTime = start;
    this.endTime = end;
    this.calendarDay = BBCalendar.getPlainDate(this.startTime);
    this.topic = topic;
  }

  public Date getStartTime() {
    return this.startTime;
  }

  public Date getEndTime() {
    return this.endTime;
  }

  public Date getCalendarDay() {
    return this.calendarDay;
  }

  public String getTopic() {
    return this.topic;
  }
}
