package org.dieschnittstelle.jee.esa.ue.jws5.junit;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.jws.ProductCRUDSOAPService;
import org.dieschnittstelle.jee.esa.jws.ProductCRUDWebService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class ProductCRUDSOAPClient {

  	private ProductCRUDWebService proxy;

  	protected static Logger logger = Logger.getLogger(ProductCRUDSOAPClient.class);

  	public ProductCRUDSOAPClient() throws Exception {

		ProductCRUDSOAPService service = new ProductCRUDSOAPService();
		ProductCRUDWebService serviceOperations = service.getProductCRUDWebServicePort();

        proxy = serviceOperations;
  	}

  	public AbstractProduct createProduct(IndividualisedProductItem prod) {
  		AbstractProduct created = proxy.createProduct(prod);
  		// as a side-effect we set the id of the created product on the argument before returning
  		prod.setId(created.getId());
  		return created;
  	}

  	public List<?> readAllProducts() {
  		return proxy.readAllProducts();
  	}

  	public AbstractProduct updateProduct(AbstractProduct update) {
  		return proxy.updateProduct(update);
  	}

  	public boolean deleteProduct(long id) {
  		return proxy.deleteProduct(id);
  	}

  	public AbstractProduct readProduct(long id) {
  		return proxy.readProduct(id);
  	}

}
