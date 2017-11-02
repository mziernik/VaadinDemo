package ui;


import com.vaadin.addon.contextmenu.ContextMenu;
import com.vaadin.addon.contextmenu.MenuItem;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.ClientRpc;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.DesignContext;
import org.jsoup.nodes.Element;

@SuppressWarnings("serial")
@Theme("valo") //base, chameleon, liferay, reindeer, runo, valo
@Title("System monitor")
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Button button = new Button("Menu");
        button.addClickListener(e -> {
            Notification.show("Kliknięto przycisk");
        });
        layout.addComponent(button);

        // Create a context menu for 'someComponent'
        ContextMenu menu = new ContextMenu(button, true);

// Basic menu item
        final MenuItem basic = menu.addItem("Basic Item", e -> {
            Notification.show("Action!");
        });
        basic.setIcon(FontAwesome.AUTOMOBILE);

// Checkable item
        final MenuItem checkable = menu.addItem("Checkable", e -> {
            Notification.show("checked: " + e.isChecked());
        });
        checkable.setIcon(FontAwesome.ANCHOR);
        checkable.setCheckable(true);
        checkable.setChecked(true);

// Disabled item
        final MenuItem disabled = menu.addItem("Disabled", e -> {
            Notification.show("disabled");
        });
        disabled.setEnabled(false);
        disabled.setIcon(FontAwesome.AMBULANCE);


    }
}

