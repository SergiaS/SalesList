package org.saleslist.repository.datajpa;

import org.saleslist.model.Payout;
import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.DESC, "dateTime");

    private final CrudProductRepository crudProductRepository;
    private final CrudPayoutRepository crudPayoutRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaProductRepository(CrudProductRepository crudProductRepository, CrudPayoutRepository crudPayoutRepository, CrudUserRepository crudUserRepository) {
        this.crudProductRepository = crudProductRepository;
        this.crudPayoutRepository = crudPayoutRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Product save(Product product, int userId) {

        int productId = product.getId() == null ? 0 : product.getId();
        Product productExistInDB = get(productId, userId);

        // product
        if (!product.isNew() && get(product.getId(), userId) == null) {
            return null;
        }
        product.setUser(crudUserRepository.getOne(userId));
        crudProductRepository.save(product);

        // payout
        if ((productExistInDB == null && product.getPayoutPercentage() > 0) ||
                (productExistInDB != null && productExistInDB.getPayoutPercentage() == 0 && product.getPayoutPercentage() > 0)) {
            Payout payout = new Payout(
                    product.getDateTime(),
                    product.getPayoutCurrency(),
                    product.getTitle());
            payout.setUser(crudUserRepository.getOne(userId));
            payout.setProduct(product);
            crudPayoutRepository.save(payout);
        } else if (productExistInDB != null && product.getPayoutPercentage() > 0) {
            crudProductRepository.updatePayout(
                    product.getDateTime(),
                    product.getPayoutCurrency(),
                    product.getTitle(),
                    productId);
        } else if (productExistInDB != null && productExistInDB.getPayoutPercentage() > 0 && product.getPayoutPercentage() == 0){
            crudPayoutRepository.deleteByProductId(product.getId());
        }

        return product;
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
    public Product getWithUser(int id, int userId) {
        return crudProductRepository.getWithUser(id, userId);
    }

    @Override
    public List<String> getOwnersNames() {
        return crudProductRepository.getOwnersNames();
    }
}
