package com.hibernate.app.repo;

import com.hibernate.app.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion,Integer> {
    Promotion findByPromoCode(@Param("promoCode") String promoCode);
    Promotion findByCustomerId(@Param("customerId") Integer customerId);

}
