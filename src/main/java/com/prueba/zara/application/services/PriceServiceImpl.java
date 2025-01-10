package com.prueba.zara.application.services;

import com.prueba.zara.application.mapper.PriceDtoMapper;
import com.prueba.zara.application.usecases.PriceService;
import com.prueba.zara.domain.constant.PriceConstant;
import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import com.prueba.zara.infrastructure.adapter.repository.PriceRepository;
import org.openapitools.model.PriceDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    private final PriceDtoMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceDtoMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public PriceDetails getPriceForProductAndBrand(Long brandId, Long productId, LocalDateTime startDate) {
        PriceEntity priceEntity = priceRepository.findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(brandId, productId, startDate)
                .stream()
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException(PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR));

        return priceMapper.toPriceDetails(priceEntity);
    }
}

