package com.prueba.zara.infrastructure.adapter.repository;

import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(Long brandId, Long productId, LocalDateTime startDate);
}
