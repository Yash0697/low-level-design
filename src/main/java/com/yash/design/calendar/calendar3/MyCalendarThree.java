package com.yash.design.calendar.calendar3;

import java.util.TreeMap;

public class MyCalendarThree {
    private TreeMap<Integer, Integer> calendar;

    public MyCalendarThree() {
        this.calendar = new TreeMap<>();
    }

    //return k booking
    public int book(int start, int end) {
        // initially book the event
        // starting point increases the number of events going on
        // ending point of an event decreases the number of events going on
        // so  key is time, value is number of events starting(+ive sign) or ending(-ive sign) at that time
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        int concurrent = 0;
        int kBooking = Integer.MIN_VALUE;
        for(Integer time: calendar.keySet()) {
            concurrent += calendar.get(time); // +ive sign(starting of events) increases the counter 
                                             //while -ive sign(ending of events) decreases it
            kBooking = Math.max(kBooking, concurrent);
        }
        return kBooking;
    }
}
