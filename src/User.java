import Data.Profile;
import java.util.HashMap;
import java.util.Map;

public class User {

  private Map<Long, Profile> users = new HashMap<>();
  private long newUserID = 0;

  public void addNewUserProfile(String name, String emailAddress, String phoneNumber) {
    Profile user = new Profile(newUserID, name, emailAddress, phoneNumber);
    users.put(newUserID++, user);
  }

  public Profile getProfileByUserID(long userID) {
    return users.get(userID);
  }

  public void displayUserProfile(long userID) {
    Profile user = users.get(userID);
    System.out.println("Information for user with ID " + userID);
    System.out.println("Name           :" + user.getName());
    System.out.println("Email Address  :" + user.getEmailAddress());
    System.out.println("Phone Number   :" + user.getPhoneNumber());
  }

  public void changeUserName(long userID, String newName) {
    users.get(userID).setName(newName);
  }

  public void changeUserEmailAddress(long userID, String newEmailAddress) {
    users.get(userID).setEmailAddress(newEmailAddress);
  }

  public void changeUserPhoneNumber(long userID, String newPhoneNumber) {
    users.get(userID).setPhoneNumber(newPhoneNumber);
  }

  public static void main(String[] args) {
    User user = new User();
    user.addNewUserProfile("qwerty", "poi@", "192391");
    user.addNewUserProfile("asdfg", "lkj@", "30239");
    user.changeUserName(0, "zxcv");
    user.addNewUserProfile("alphabet", "abc@", "30239");
    user.changeUserEmailAddress(2, "abcabc@");
    user.changeUserPhoneNumber(2, "000111111000");
    user.displayUserProfile(1);
    user.displayUserProfile(0);
    user.displayUserProfile(2);
  }
}
