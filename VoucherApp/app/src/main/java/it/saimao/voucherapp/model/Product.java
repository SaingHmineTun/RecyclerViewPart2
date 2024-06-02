package it.saimao.voucherapp.model;

public record Product(
        String imageUrl,
        String name,
        Double price,
        Integer quantity
) {
}
