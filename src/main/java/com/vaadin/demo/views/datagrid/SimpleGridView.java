package com.vaadin.demo.views.datagrid;

import com.vaadin.demo.data.SamplePerson;
import com.vaadin.demo.data.SamplePersonRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;


@PageTitle("Simple Grid")
@Route("simple-grid")
@Menu(order = 3, icon = LineAwesomeIconUrl.TH_LARGE_SOLID)
public class SimpleGridView extends VerticalLayout {

    private final GridListDataView<SamplePerson> dataView;

    public SimpleGridView() {
        setSizeFull();

        // 1. add Grid
        Grid<SamplePerson> grid = new Grid<>(SamplePerson.class);
        grid.setColumns("firstName", "lastName", "email");
        grid.setSizeFull();

        // 2. load data from repository
        dataView = grid.setItems(SamplePersonRepository.findAll());

        // 3. add textfield
        TextField filterField = new TextField();
        filterField.setPlaceholder("Search by name...");
        filterField.setWidthFull();
        filterField.setClearButtonVisible(true);

        // 4. add filter
        filterField.addValueChangeListener(event -> {
            String searchText = event.getValue();
            if (searchText == null || searchText.trim().isEmpty()) {
                dataView.removeFilters();
            } else {
                String lowerCaseFilter = searchText.toLowerCase().trim();
                dataView.addFilter(person ->
                        person.getFirstName().toLowerCase().startsWith(lowerCaseFilter) ||
                                person.getLastName().toLowerCase().startsWith(lowerCaseFilter)
                );
            }
        });

        // add components to view
        add(filterField, grid);
    }
}
