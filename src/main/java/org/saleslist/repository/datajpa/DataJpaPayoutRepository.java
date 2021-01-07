package org.saleslist.repository.datajpa;

import org.saleslist.model.Payout;
import org.saleslist.repository.PayoutRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class DataJpaPayoutRepository implements PayoutRepository {

    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.DESC, "dateTime");

    private final CrudPayoutRepository crudPayoutRepository;

    public DataJpaPayoutRepository(CrudPayoutRepository crudPayoutRepository) {
        this.crudPayoutRepository = crudPayoutRepository;
    }

    @Override
    public Payout save(Payout payout, int userId) {
        if (!payout.isNew() && get(payout.getId(), userId) == null) {
            return null;
        }
        return crudPayoutRepository.save(payout);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            crudPayoutRepository.deleteById(id);
        }
        return crudPayoutRepository.delete(id, userId) != 0;
    }

    @Override
    public Payout get(int id, int userId) {
        if (userId == ADMIN_ID) {
            return crudPayoutRepository.getOne(id);
        }

        return crudPayoutRepository.findById(id)
                .filter(payout -> payout.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Payout> getAll(int userId) {
        if (userId == ADMIN_ID){
            return crudPayoutRepository.findAll(SORT_DATE_TIME);
        }
//        return crudPayoutRepository.getAll(userId);
        return crudPayoutRepository.findAll(SORT_DATE_TIME).stream()
                .filter(payout -> payout.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payout> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        if (userId == ADMIN_ID) {
            return crudPayoutRepository.getBetweenAdmin(startDateTime, endDateTime);
        }
        return crudPayoutRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Payout getWithUser(int id, int userId) {
        return crudPayoutRepository.getWithUser(id, userId);
    }

    @Override
    public List<String> getOwnersNames() {
        return crudPayoutRepository.getOwnersNames();
    }
}
