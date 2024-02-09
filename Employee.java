import java.util.ArrayList;
import java.util.List;

public class Employee  {
   private String name;
   private String uid;
   private List<CalendarTime> signInTimes;
   private List<CalendarTime> signOutTimes;

 public Employee(String name, String uid) {
     this.name = name;
     this.uid = uid;
     this.signInTimes = new ArrayList<>();
      this.signOutTimes = new ArrayList<>();
 }

public String name()    {
        return name;
    }

 public String UID()   {
        return uid;
  }

 public void signIn(Date d, Time t)     {
        signInTimes.add(new CalendarTime(d, t));
 }

   public void signOut(Date d, Time t) {
              signOutTimes.add(new CalendarTime(d, t));
    }

    public boolean present() {
              return !signInTimes.isEmpty() && signInTimes.size() == signOutTimes.size() + 1;
  }

    public boolean worked(Date d) {
        for (int i = 0; i < signInTimes.size(); i++) {
            if (signInTimes.get(i).date().equals(d) || (signOutTimes.size() > i && signOutTimes.get(i).date().equals(d))) {
                return true;
      }
         }
     return false;
    }

    public boolean worked(Week w) {
        // Check if the employee worked at least one shift during the given week
        for (int i = 0; i < signInTimes.size(); i++) {
            if (w.includes(signInTimes.get(i).date()) || (signOutTimes.size() > i && w.includes(signOutTimes.get(i).date()))) {
                return true;  }
       }
      return false;
    }

    public List<CalendarTime> get(Date d) {
        // Create a list of CalendarTime objects that include the given date
        List<CalendarTime> shifts = new ArrayList<>();
        for (int i = 0; i < signInTimes.size(); i++) {
         CalendarTime signInTime = signInTimes.get(i);
         CalendarTime signOutTime = (i < signOutTimes.size()) ? signOutTimes.get(i) : null;
            if (signInTime.date().equals(d) || (signOutTime != null && signOutTime.date().equals(d))) {
              shifts.add(signInTime);
               if (signOutTime != null) {
                shifts.add(signOutTime);
                    }
            }
        }
           return shifts;
    }

    public List<CalendarTime> get(Week w) {
        // Create a list of CalendarTime objects that overlap with the given week
      List<CalendarTime> shifts = new ArrayList<>();
        for (int i = 0; i < signInTimes.size(); i++) {
         CalendarTime signInTime = signInTimes.get(i);
         CalendarTime signOutTime = (i < signOutTimes.size()) ? signOutTimes.get(i) : null;
          if (w.includes(signInTime.date())) {
                shifts.add(signInTime);
            }
            if (signOutTime != null && w.includes(signOutTime.date())) {
                shifts.add(signOutTime);
             }
            }
        return shifts;
    }
    
 public Duration hours(Week w) {
     Duration totalHours= new Duration(0);
     List<CalendarTime> shifts =get(w);
     boolean inShift = false;
      CalendarTime shiftStart =null;
       for (CalendarTime time : shifts) {
            if (inShift) {
              totalHours = totalHours.add(time.subtract(shiftStart));
              inShift = false;
            } else {
               shiftStart = time;
              inShift= true;
          }
        }
        return totalHours;
      }

}
