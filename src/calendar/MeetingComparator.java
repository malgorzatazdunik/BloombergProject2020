import java.util.Comparator;

public class MeetingComparator implements Comparator<Meeting> {
    public int compare(Meeting a, Meeting b) {
        return a.getStartTime().compareTo(b.getStartTime());
    }
} 