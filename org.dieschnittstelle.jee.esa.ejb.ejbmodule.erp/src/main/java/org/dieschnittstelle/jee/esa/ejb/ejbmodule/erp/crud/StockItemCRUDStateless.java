package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.jee.esa.entities.erp.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.dieschnittstelle.jee.esa.utils.Utils.show;

@Stateless
@Local(StockItemCRUDLocal.class)
public class StockItemCRUDStateless implements  StockItemCRUDLocal{

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
//        if(!em.contains(item.getProduct())){
//            item.setProduct(em.merge(item.getProduct()));
//        }
//        em.persist(item);

        return em.merge(item);
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        return em.find(StockItem.class, new ProductAtPosPK(prod,pos));
    }


    @Override
    public StockItem updateStockItem(StockItem item) {
        return em.merge(item);
    }

    @Override
    public List<StockItem> readAllStockItems() {
        Query query = em.createQuery("FROM StockItem");

        List<StockItem> sis = (List<StockItem>) query
                .getResultList();

        return sis;
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {

        Query query = em
                .createQuery("SELECT t FROM StockItem t WHERE t.product = "
                        + prod.getId());

        List<StockItem> trans = query.getResultList();

        return trans;
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos){
        Query query = em
                .createQuery("SELECT t FROM StockItem t WHERE t.pos = "
                        + pos.getId());

        List<StockItem> trans = query.getResultList();

        return trans;

    }
}
