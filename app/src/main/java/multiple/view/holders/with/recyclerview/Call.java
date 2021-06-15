package multiple.view.holders.with.recyclerview;

public class Call
{
    private String callerName,callTime;

    public Call(String callerName, String callTime)
    {
        this.callerName = callerName;
        this.callTime = callTime;
    }

    public String getCallerName()
    {
        return callerName;
    }

    public String getCallTime() {
        return callTime;
    }
}

