package org.saleslist.web;

import org.saleslist.model.Payout;
import org.saleslist.repository.jdbc.JdbcPayoutRepository;
import org.saleslist.web.user.MainServlet;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;
import static org.saleslist.web.SecurityUtil.getAuthUserId;

@WebServlet("/payouts")
public class PayoutServlet extends MainServlet {

	private JdbcPayoutRepository payoutRepository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		payoutRepository = springContext.getBean(JdbcPayoutRepository.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		Payout payout = new Payout(
				LocalDateTime.parse(request.getParameter("dateTime")),
				new BigDecimal(request.getParameter("amount").replace(",", ".")),
				request.getParameter("notes").trim()
		);

		if (!StringUtils.isEmpty(request.getParameter("id"))) {
			int payoutId = getId(request);
			payout.setId(payoutId);
		}
		payoutRepository.save(payout, getAuthUserId());

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
//		super.doGet(request, response);
		String action = request.getParameter("action");

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

	private Payout getDefaultPayout() {
		return new Payout(
				LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
				new BigDecimal("0"),
				"");
	}
}
