package com.infoshareacademy.menu;

import java.util.Stack;

public class Breadcrumbs {
    private static Breadcrumbs breadcrumbsInstance = null;
    private Stack<String> breadcrumbsStack;

    private Breadcrumbs() {
        breadcrumbsStack = new Stack<>();
    }

    public static Breadcrumbs getInstance() {
        if (breadcrumbsInstance == null) {
            breadcrumbsInstance = new Breadcrumbs();
        }
        return breadcrumbsInstance;
    }

    public void addBreadcrumb(String currentMenu) {
        breadcrumbsStack.push(currentMenu);
    }

    public void removeBreadcrumb() {
        breadcrumbsStack.pop();
    }

    public String displayBreadcrumb() {
        return ">>>>> " + breadcrumbsStack.stream()
                .reduce((result, element) -> result + " / " + element)
                .orElse("") + "\n";
    }


}
