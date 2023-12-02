package org.example.classes;

import jdk.jfr.Event;

import java.util.EventListener;

public class ThisEscape {
    public static void main(String[] args) {
        ThisEscape thisEscape = new ThisEscape(new EventSource());
    }

    //bad practice
    public ThisEscape(EventSource source) {
        EventListener eventListener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
        source.registerListener(
            eventListener
        );
    }

    private void doSomething(Event e) {

    }


    private static class EventSource {
        public void registerListener(EventListener eventListener) {
        }

    }
}