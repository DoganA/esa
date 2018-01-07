package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import java.util.List;

public class ProductCRUDWebServiceRESTImpl implements IProductCRUDWebServiceREST {
    @Override
    public List<IndividualisedProductItem> readAllProducts() {
        return null;
    }

    @Override
    public IndividualisedProductItem createProduct(IndividualisedProductItem touchpoint) {
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }

    @Override
    public IndividualisedProductItem readProduct(long id) {
        return null;
    }

    @Override
    public IndividualisedProductItem updateProduct(IndividualisedProductItem touchpoint) {
        return null;
    }
}
