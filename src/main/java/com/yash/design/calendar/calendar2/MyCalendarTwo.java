package com.yash.design.calendar.calendar2;

import java.util.TreeMap;

public class MyCalendarTwo {
    private TreeMap<Integer, Integer> calendar; // stores how many events are going  on at a given point of time

    public MyCalendarTwo() {
        calendar = new TreeMap<>();
    }

    //avoid triple booking
    public boolean book(int start, int end) {
        // initially book the event
        // starting point increases the number of events going on
        // ending point of an event decreases the number of events going on
        // so  key is time, value is number of events starting(+ive sign) or ending(-ive sign) at that time
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        int concurrent = 0;

        for(Integer time: calendar.keySet()) {
            concurrent += calendar.get(time); // +ive sign(starting of events) increases the counter 
                                             //while -ive sign(ending of events) decreases it
            if(concurrent > 2) {
                //can't schedule this event, revert the booked event
                calendar.put(start, calendar.getOrDefault(start, 0) - 1);
                calendar.put(end, calendar.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }
}
