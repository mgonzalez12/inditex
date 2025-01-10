package com.prueba.zara.domain.constant;

public class PriceConstant {
    private static final PriceConstant INSTANCE = new PriceConstant();
    PriceConstant() {}

    public static PriceConstant getInstance() { return INSTANCE;}
    public static final String PRICE_NOT_FOUND_MESSAGE_ERROR = "Precio no encontrado para los parámetros dados.";
    public static final String PRICE_MESSAGE_INTERNAL_SERVER_ERROR = "Internal server error.";
}
