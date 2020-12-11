package org.saleslist.web;

import org.saleslist.model.Payout;
import org.saleslist.repository.jdbc.JdbcMainRepository;
import org.saleslist.repository.jdbc.JdbcPayoutRepository;
import org.saleslist.web.controller.PayoutRestController;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.saleslist.util.DateTimeUtil.parseLocalDate;
import static org.saleslist.util.DateTimeUtil.parseLocalTime;
import static org.saleslist.web.SecurityUtil.ADMIN_ID;
import static org.saleslist.web.SecurityUtil.getAuthUserId;

@WebServlet("/payouts")
public class PayoutServlet extends MainServlet<Payout> {

    private PayoutRestController controller;
    private JdbcMainRepository<Payout> repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = springContext.getBean(PayoutRestController.class);
        repository = springContext.getBean(JdbcPayoutRepository.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Payout payout = fillModel(request);

        if (!StringUtils.isEmpty(request.getParameter("id"))) {
            int modelId = getId(request);
            payout.setId(modelId);
            controller.update(payout, payout.getId());
        } else {
            controller.create(payout);
        }

        response.sendRedirect(getTableName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                controller.delete(getId(request));
                response.sendRedirect("payouts");
            }
            case "create", "update" -> {
                final Payout payout = "create".equals(action) ?
                        getDefaultPayout() : controller.get(getId(request));

                request.setAttribute("payout", payout);
                request.getRequestDispatcher("/payout-form.jsp").forward(request, response);
            }
            case "filter" -> {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

                if (getAuthUserId() == ADMIN_ID) {
                    request.setAttribute("owners", repository.getOwnersNames());
                }

                request.setAttribute("userId", getAuthUserId());
                request.setAttribute("payouts", controller.getBetween(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/payouts.jsp").forward(request, response);
            }
            default -> {
                if (getAuthUserId() == ADMIN_ID) {
                    request.setAttribute("owners", repository.getOwnersNames());
                }

                request.setAttribute("userId", getAuthUserId());
                request.setAttribute("payouts", controller.getAll());
                request.getRequestDispatcher("/payouts.jsp").forward(request, response);
            }
        }
    }

    private Payout getDefaultPayout() {
        return new Payout(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                new BigDecimal("0"),
                "");
    }

    @Override
    protected Payout fillModel(HttpServletRequest request) {
        return new Payout(
                LocalDateTime.parse(request.getParameter("dateTime")).truncatedTo(ChronoUnit.MINUTES),
                new BigDecimal(request.getParameter("amount").replace(",", ".")),
                request.getParameter("notes").trim()
        );
    }

    @Override
    protected String getTableName() {
        return "payouts";
    }
}
