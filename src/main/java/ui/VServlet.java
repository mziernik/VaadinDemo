package ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/", "/*", "/VAADIN/*"}, asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "WidgetSet")
public class VServlet extends VaadinServlet {

    public VServlet() {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        super.service(request, response);
    }
}
