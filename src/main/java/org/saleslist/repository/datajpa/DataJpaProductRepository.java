package org.saleslist.repository.datajpa;

import org.saleslist.model.Payout;
import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.DESC, "date_time");

    private final CrudProductRepository crudProductRepository;
    private final CrudPayoutRepository crudPayoutRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaProductRepository(CrudProductRepository crudProductRepository, CrudPayoutRepository crudPayoutRepository, CrudUserRepository crudUserRepository) {
        this.crudProductRepository = crudProductRepository;
        this.crudPayoutRepository = crudPayoutRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Product save(Product product, int userId) {

        if (product.getPayoutPercentage() > 0) {
            Payout payout = new Payout(
                    product.getDateTime(),
                    product.getPayoutCurrency(),
                    product.getTitle());
            crudPayoutRepository.save(payout);
        } else if(get(product.getId(), userId).getPayoutPercentage() > 0 && product.getPayoutPercentage() == 0){
            crudPayoutRepository.deleteByProductId(product.getId());
        }

        if (!product.isNew() && get(product.getId(), userId) == null) {
            return null;
        }
        product.setUser(crudUserRepository.getOne(userId));
        return crudProductRepository.save(product);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            crudProductRepository.deleteById(id);
            return true;
        }
        return crudProductRepository.delete(id, userId) != 0;
    }

    @Override
    public Product get(int id, int userId) {
        if (userId == ADMIN_ID) {
            return crudProductRepository.getOne(id);
        }
        return crudProductRepository.findById(id)
                .filter(product -> product.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Product> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return crudProductRepository.findAll(SORT_DATE_TIME);
        }
        return crudProductRepository.getAll(userId);
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        if (userId == ADMIN_ID) {
            return crudProductRepository.getBetweenAdmin(startDateTime, endDateTime);
        }
        return crudProductRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<String> getOwnersNames() {
        return crudProductRepository.getOwnersNames();
    }
}
