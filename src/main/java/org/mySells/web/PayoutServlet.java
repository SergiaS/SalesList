package org.mySells.web;

import org.mySells.jdbc.model.Payout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet("/payouts")
public class PayoutServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(PayoutServlet.class);

//	private JdbcPayoutRepository payoutRepository;
//	private JdbcProductRepository productRepository;

	@Override
	public void init() throws ServletException {
//		payoutRepository = new JdbcPayoutRepository();
//		productRepository = new JdbcProductRepository();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Payout payout = new Payout();
		payout.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
		payout.setAmount(Double.parseDouble(request.getParameter("amount").replace(",", ".")));
		payout.setNotes(request.getParameter("notes").trim());

//		if (request.getParameter("id").equals("0")) {
//			payoutRepository.add(payout);
//		} else {
//			if (!request.getParameter("productId").equals("0")) {
//				payout.setProductId(Integer.parseInt(request.getParameter("productId")));
//			}
//			int id = getId(request);
//			payoutRepository.update(id, payout);
//		}

		response.sendRedirect("payouts");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		logger.info("doGet: PATH = {}, ACTION = {}", request.getServletPath(), action);

//		PayoutsStats ps = new PayoutsStats();
//		request.setAttribute("payoutsStats", ps);

//		switch (action == null ? "all" : action) {
//			case "delete" -> {
//				int id = getId(request);
//				payoutRepository.delete("id", id);
//				response.sendRedirect("payouts");
//			}
//			case "create", "update" -> {
//				Payout payout = "create".equals(action) ? getDefaultPayout() : payoutRepository.getPayoutById(getId(request));
//				request.setAttribute("payout", payout);
//				request.getRequestDispatcher("/payout-form.jsp").forward(request, response);
//			}
//			default -> {
//				request.setAttribute("payouts", payoutRepository.getAllPayouts());
//				request.getRequestDispatcher("/payouts.jsp").forward(request, response);
//			}
//		}
	}

	private int getId(HttpServletRequest request) {
		String paramId = request.getParameter("id");
		return Integer.parseInt(paramId);
	}

	private Payout getDefaultPayout() {
		return new Payout(
				LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
				0,
				"");
	}
}
