package com.vaadin.demo.views.empty;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Empty")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class EmptyView extends VerticalLayout {

    public EmptyView() {
    }
}
