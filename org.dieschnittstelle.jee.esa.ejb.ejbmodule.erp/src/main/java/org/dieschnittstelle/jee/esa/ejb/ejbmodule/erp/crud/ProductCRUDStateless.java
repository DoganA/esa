package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

import static org.dieschnittstelle.jee.esa.utils.Utils.show;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote, ProductCRUDLocal {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        em.persist(prod);
        show("createProduct "+prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        Query query = em.createQuery("FROM AbstractProduct");

        List<AbstractProduct> ps = (List<AbstractProduct>) query
                .getResultList();

        return ps;
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        return em.merge(update);
    }

    @Override
    public AbstractProduct readProduct(long productID) {

        return em.find(AbstractProduct.class, productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        AbstractProduct aP = em.find(AbstractProduct.class, productID);
        em.remove(aP);
        return !em.contains(aP);
    }
}
