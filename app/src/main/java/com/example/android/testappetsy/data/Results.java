package com.example.android.testappetsy.data;

import java.util.List;

public class Results {

    private List<Product> productList;
    private List<List<Image>> imageList;

    private Product product;
    private Image image;

    public Results(List<Product> productList, List<List<Image>> imageList) {
        this.productList = productList;
        this.imageList = imageList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<List<Image>> getImageList() {
        return imageList;
    }

    public Product getProduct() {
        return product;
    }

    public Image getImage() {
        return image;
    }
}
