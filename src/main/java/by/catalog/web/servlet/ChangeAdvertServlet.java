package by.catalog.web.servlet;

import by.catalog.domain.Advert;
import by.catalog.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "ChangeCurrentAdvertServlet", urlPatterns = "/changeCurrentAdvert")
public class ChangeAdvertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int advertNumber = (int) req.getAttribute("advertNumber");
        List<Advert> currentUserAdverts = (List<Advert>) req.getSession().getAttribute("currentUserAdverts");
        Advert advert = currentUserAdverts.get(advertNumber);
        req.setAttribute("currentAdvert", advert);
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        String currentUserName = currentUser.getName();
        req.setAttribute("currentUserName", currentUserName);
        req.getServletContext().getRequestDispatcher("/pages/changeCurrentAdvert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advert currentAdvert = (Advert) req.getAttribute("currentAdvert");
        String newModel = req.getParameter("newModel");
        String newColor = req.getParameter("newColor");
        String newYear = req.getParameter("newYear");
        String newPrice = req.getParameter("newPrice");
        String newDescription = req.getParameter("newDescription");
        if (newModel != null) {
            currentAdvert.setModelCar(newModel);
        }
        if (newColor != null) {
            currentAdvert.setModelCar(newColor);
        }
        if (newYear != null) {
            currentAdvert.setModelCar(newYear);
        }
        if (newPrice != null) {
            currentAdvert.setModelCar(newPrice);
        }
        if (newDescription != null) {
            currentAdvert.setModelCar(newDescription);
        }

        List<Advert> currentUserAdverts = (List<Advert>) req.getSession().getAttribute("currentUserAdverts");
        req.setAttribute("currentAdvert", currentAdvert);
        getServletContext().getRequestDispatcher("/pages/chooseAdvertToChange.jsp").forward(req, resp);
        }
    }
