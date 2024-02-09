public class TimePeriod {
    private final Duration lowerBound;
    private final Duration upperBound;

    public TimePeriod(Duration lowerBound, Duration upperBound)  {
        this.lowerBound =lowerBound;
        this.upperBound= upperBound;
     }

    public Duration lowerBound()  {
        return lowerBound ;
      }
 
    public Duration upperBound()  {
        return upperBound;
     }

    public boolean includes(Duration duration)     {
        return (duration.compareTo(lowerBound) >=0 && duration.compareTo(upperBound) <0);
    }

    public boolean precedes(TimePeriod other) {
        return this.upperBound.compareTo(other.lowerBound) <=0;
      }

    public boolean adjacent(TimePeriod other) {
    return this.upperBound.compareTo(other.lowerBound) == 0 || other.upperBound.compareTo(this.lowerBound) == 0;
   }


    public String toString()    {
        return "[" + Duration.format(lowerBound, "minute") + " .. " + Duration.format(upperBound, "minute") + "]";
      }
 }
