package com.yash.design.calendar.calendar1;
import java.util.TreeMap;
import java.util.Map.Entry;

public class MyCalendar {
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    //avoid double booking
    public boolean book(int start, int end) {
        Entry<Integer, Integer> prev = calendar.floorEntry(start);
        Entry<Integer, Integer> next = calendar.ceilingEntry(start);

        // if start time is before the previous event, or end time is after the next event we can't book it
        if(prev != null && prev.getValue() > start) return false;
        if(next != null && next.getKey() < end) return false;
        calendar.put(start, end);
        return true;
    }
}
