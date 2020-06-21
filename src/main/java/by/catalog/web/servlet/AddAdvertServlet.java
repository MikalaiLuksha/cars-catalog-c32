package by.catalog.web.servlet;

import by.catalog.domain.User;
import by.catalog.service.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addAdvert", name = "AddAdvertServlet")

public class AddAdvertServlet extends HttpServlet {


    AdvertService advertService = new AdvertService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark != null){
        List listCar = (List) advertService.returnModelByMark(mark);
        req.setAttribute("checkModelCar", true);
        req.setAttribute("listCar", listCar);
}
        getServletContext().getRequestDispatcher("/pages/addAdvert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUSer = (User) req.getSession().getAttribute("currentUser");
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        int year = Integer.parseInt(req.getParameter("year"));
        double prise = Double.parseDouble(req.getParameter("prise"));
        long id_user = currentUSer.getId();
        advertService.saveAdvert(model, color, year, prise, id_user);
        req.setAttribute("messageAccount", "Advert successfully added");
        resp.sendRedirect("/");
    }
}
