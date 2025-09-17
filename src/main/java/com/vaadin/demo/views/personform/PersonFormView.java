package com.vaadin.demo.views.personform;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Person Form")
@Route("person-form")
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
public class PersonFormView extends VerticalLayout {

    public PersonFormView() {
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        setJustifyContentMode(JustifyContentMode.START);
        setAlignItems(Alignment.CENTER);

        H3 h3 = new H3("Personal Information");
        h3.setWidth("100%");

        FormLayout formLayout2Col = new FormLayout();
        formLayout2Col.setWidth("100%");

        TextField textField = new TextField("First Name");
        TextField textField2 = new TextField("Last Name");
        DatePicker datePicker = new DatePicker("Birthday");
        TextField textField3 = new TextField("Phone Number");
        EmailField emailField = new EmailField("Email");
        TextField textField4 = new TextField("Occupation");

        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(datePicker);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        formLayout2Col.add(textField4);

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");

        Button buttonPrimary = new Button("Save");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.setWidth("min-content");
        Button buttonSecondary = new Button("Cancel");
        buttonSecondary.setWidth("min-content");

        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);

        add(h3);
        add(formLayout2Col);
        add(layoutRow);
    }
}
