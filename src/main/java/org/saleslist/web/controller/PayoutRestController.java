package org.saleslist.web.controller;

import org.saleslist.model.Payout;
import org.saleslist.service.PayoutService;
import org.saleslist.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.saleslist.util.ValidationUtil.assureIdConsistent;
import static org.saleslist.util.ValidationUtil.checkNew;

@Controller
public class PayoutRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    private final PayoutService service;

    public PayoutRestController(PayoutService service) {
        this.service = service;
    }

    public Payout get(int id) {
        int userId = SecurityUtil.getAuthUserId();
        log.info("get payout {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.getAuthUserId();
        log.info("delete payout {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Payout> getAll() {
        int userId = SecurityUtil.getAuthUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Payout create(Payout payout) {
        int userId = SecurityUtil.getAuthUserId();
        checkNew(payout);
        log.info("create {} for user {}", payout, userId);
        return service.create(payout, userId);
    }

    public void update(Payout payout, int id) {
        int userId = SecurityUtil.getAuthUserId();
        assureIdConsistent(payout, id);
        log.info("update {} for user {}", payout, userId);
        service.update(payout, userId);
    }

    public List<Payout> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                      @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = SecurityUtil.getAuthUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        return service.getBetweenInclusive(startDate, endDate, userId);
    }
}
