package com.nary.tests.narytreetraversal.model;

public enum Browser {
    FIREFOX("Firefox"),
    CHROME("Chrome");

    String name;

    Browser(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser getBrowserByString(String name) {
        final Browser[] browsers = Browser.values();
        for (Browser browser : browsers) {
            if (browser.getName().equals(name)) {
                return browser;
            }
        }
        return FIREFOX;  //Passing Firefox as the default browser if no browser is passed
    }
}

