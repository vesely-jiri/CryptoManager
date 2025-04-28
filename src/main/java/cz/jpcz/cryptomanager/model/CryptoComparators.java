package cz.jpcz.cryptomanager.model;

import cz.jpcz.cryptomanager.exception.UnknownSortParameterException;

import java.util.Comparator;

public class CryptoComparators {
    private static final Comparator<Crypto> BY_NAME = Comparator.comparing(Crypto::getName);
    private static final Comparator<Crypto> BY_PRICE = Comparator.comparing(Crypto::getPrice);
    private static final Comparator<Crypto> BY_QUANTITY = Comparator.comparing(Crypto::getQuantity);
    private static final Comparator<Crypto> BY_SYMBOL = Comparator.comparing(Crypto::getSymbol);

    private CryptoComparators() {}

    public static Comparator<Crypto> getComparator(String comparator) {
        return switch (comparator) {
            case "name" -> BY_NAME;
            case "price" -> BY_PRICE;
            case "quantity" -> BY_QUANTITY;
            case "symbol" -> BY_SYMBOL;
            default -> throw new UnknownSortParameterException(comparator);
        };
    }
}