package com.example.demo;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Rubin's Game")
@Route(value = "/test")
public class TestView extends VerticalLayout {
    TestView(){
        this.add(new Label("You landed on a page which is unfortunately under construction"));
    }
}
