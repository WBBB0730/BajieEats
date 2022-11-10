package cn.edu.szu.Bajie.constant;


import java.util.concurrent.TimeUnit;

/**
 * @author Whitence
 * @date 20221008
 */
public enum CacheTimeInterval {

    /**
     * 30s
     */
    THIRTY_SECOND(30,TimeUnit.SECONDS),

    /**
     * 1min
     */
    ONE_MIN(1,TimeUnit.MINUTES),

    /**
     * 3min
     */
    THREE_MIN(3,TimeUnit.MINUTES),

    /**
     * 5min
     */
    FIVE_MIN(5,TimeUnit.MINUTES),

    /**
     * 10min
     */
    TEN_MIN(10,TimeUnit.MINUTES),

    /**
     * 30min
     */

    THIRTY_MIN(30,TimeUnit.MINUTES),

    /**
     * 1h
     */
    AN_HOUR(1,TimeUnit.HOURS),

    /**
     * 1d
     */
    A_DAY(1,TimeUnit.DAYS)
    ;
    private long time;

    private TimeUnit timeUnit;

    CacheTimeInterval(long time, TimeUnit timeUnit) {
        this.time=time;
        this.timeUnit=timeUnit;
    }

    public long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}

