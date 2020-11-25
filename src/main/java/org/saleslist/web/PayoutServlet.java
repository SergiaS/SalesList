package org.saleslist.web;

import org.saleslist.model.Payout;
import org.saleslist.repository.jdbc.JdbcPayoutRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;
import static org.saleslist.web.SecurityUtil.getAuthUserId;

@WebServlet("/payouts")
public class PayoutServlet extends HttpServlet {

	private ConfigurableApplicationContext springContext;
	private JdbcPayoutRepository payoutRepository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
		payoutRepository = springContext.getBean(JdbcPayoutRepository.class);
	}

	@Override
	public void destroy() {
		springContext.close();
		super.destroy();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//		Payout payout = new Payout();
//		payout.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
////		payout.setAmount(Double.parseDouble(request.getParameter("amount").replace(",", ".")));
//		payout.setNotes(request.getParameter("notes").trim());
//
//		if (request.getParameter("id").equals("0")) {
//			payoutRepository.add(payout);
//		} else {
//			if (!request.getParameter("productId").equals("0")) {
//				payout.setUserId(Integer.parseInt(request.getParameter("productId")));
//			}
//			int id = getId(request);
//			payoutRepository.update(id, payout);
//		}

		response.sendRedirect("payouts");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

//		PayoutsStats ps = new PayoutsStats();
//		request.setAttribute("payoutsStats", ps);

		switch (action == null ? "all" : action) {
			case "delete" -> {
				payoutRepository.delete(getId(request), getAuthUserId());
				response.sendRedirect("payouts");
			}
			case "create", "update" -> {
				final Payout payout = "create".equals(action) ?
						getDefaultPayout() : payoutRepository.get(getId(request), getAuthUserId());

				request.setAttribute("payout", payout);
				request.getRequestDispatcher("/payout-form.jsp").forward(request, response);
			}
			default -> {
				if (getAuthUserId() == ADMIN_ID) {
					request.setAttribute("owners", payoutRepository.getOwnersNames());
				}

				request.setAttribute("userId", getAuthUserId());
				request.setAttribute("payouts", payoutRepository.getAll(getAuthUserId()));
				request.getRequestDispatcher("/payouts.jsp").forward(request, response);
			}
		}
	}

	private int getId(HttpServletRequest request) {
		String paramId = request.getParameter("id");
		return Integer.parseInt(paramId);
	}

	private Payout getDefaultPayout() {
		return new Payout(
				LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
				new BigDecimal("0"),
				"");
	}
}
