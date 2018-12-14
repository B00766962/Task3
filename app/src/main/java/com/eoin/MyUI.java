package com.eoin;

import java.util.*;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout(); // master layout is horizontal? 


        Label logo = new Label("<H1>Party Planners</H1> <p/> <h3>Please enter the details below and click Book</h3><br>", ContentMode.HTML);
      
        
        TextField name = new TextField("Name of Person");
         
        TextField phone = new TextField("Phone number");

        TextField staffNumber = new TextField("Staf Number");


        ComboBox<String> gender = new ComboBox<String>("Gender");

        gender.setItems("female", "male", "other");

        Button addButton = new Button("Add");
 
        final VerticalLayout vlayout = new VerticalLayout(); // another layout inside the main layout


        // Create List of people makes it into an array I think
        List<Person> people = new ArrayList<Person>();
               
        // Create Grid then Call not sure what the people bit means
        Grid<Person> myGrid = new Grid<> ();
        myGrid.setItems(people);
        myGrid.addColumn(Person::getName).setCaption("Name");
        myGrid.addColumn(Person::getPhone).setCaption("Phone");
        myGrid.addColumn(Person::getGender).setCaption("Gender");
        myGrid.addColumn(Person::getStaffNumber).setCaption("Staff Number");

        Button clear = new Button("Clear");
        clear.addClickListener(e -> {
            myGrid.removeAllColumns();
            people.clear(); // Clear the list of people, people is a list this is an inbult function
            name.setValue("");
            phone.setValue("");
            gender.setValue("");
        });
        
        
        addButton.addClickListener(e -> {
            people.add(new Person(name.getValue(),phone.getValue(),gender.getValue(),staffNumber.getValue())); // add to the people List, create new 
            myGrid.removeAllColumns(); // get everything removed from grid
            myGrid.setItems(people); // add everything back into grid
            // add columns again
            myGrid.addColumn(Person::getName).setCaption("Name");
            myGrid.addColumn(Person::getPhone).setCaption("Phone");
            myGrid.addColumn(Person::getGender).setCaption("Gender");
            myGrid.addColumn(Person::getStaffNumber).setCaption("Staff Number");
        });
        
        vlayout.addComponents(name, phone, gender, staffNumber, addButton, clear);        
        layout.addComponents(logo, vlayout , myGrid); 
           
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
