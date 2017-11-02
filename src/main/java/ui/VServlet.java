package ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/ui", "/ui/*", "/VAADIN/*"}, asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
public class VServlet extends VaadinServlet {

    public VServlet() {
        System.out.println("sdffgdfg");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        super.service(request, response);
    }
}
