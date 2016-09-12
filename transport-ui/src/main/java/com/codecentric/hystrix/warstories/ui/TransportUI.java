package com.codecentric.hystrix.warstories.ui;

import org.springframework.beans.factory.annotation.Autowired;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.codecentric.hystrix.warstories.ui.service.ConnoteService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Theme("valo")
@SpringUI(path = "")
public class TransportUI extends UI {

    @Autowired
    private ConnoteService connoteService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Connote: "));

        FieldGroup fieldGroup = new BeanFieldGroup<ConnoteDTO>(ConnoteDTO.class);

        fieldGroup.setItemDataSource(new BeanItem<ConnoteDTO>(connoteService.getConnote()));

        // Loop through the properties, build fields for them and add the fields
        // to this UI
        for (Object propertyId : fieldGroup.getUnboundPropertyIds()) {
            verticalLayout.addComponent(fieldGroup.buildAndBind(propertyId));
        }

        setContent(verticalLayout);

    }
}
