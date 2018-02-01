package org.dieschnittstelle.jee.esa.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.Campaign;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.ProductType;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer 
 * die Umetzung der beiden Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>, 
 * die Sie aus dem ServletContext auslesen koennen
 */
@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", serviceName = "ProductCRUDSOAPService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProductCRUDWebService {

	protected static Logger logger = Logger
			.getLogger(ProductCRUDWebService.class);

	@Resource
	private WebServiceContext wscontext;

	public List<AbstractProduct> readAllProducts() {
		logger.info("readAllProducts()");

		GenericCRUDExecutor<AbstractProduct> productCRUD = getCRUDExecutorFromWsContext();
		return productCRUD.readAllObjects();
	}

	public AbstractProduct createProduct(AbstractProduct product) {
		logger.info("createProduct()");

		GenericCRUDExecutor<AbstractProduct> productCRUD = getCRUDExecutorFromWsContext();
		return productCRUD.createObject(product);
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		logger.info("updateProduct()");

		GenericCRUDExecutor<AbstractProduct> productCRUD = getCRUDExecutorFromWsContext();
		return productCRUD.updateObject(update);
	}

	public boolean deleteProduct(long id) {
		logger.info("deleteProduct()");

		GenericCRUDExecutor<AbstractProduct> productCRUD = getCRUDExecutorFromWsContext();
		return productCRUD.deleteObject(id);
	}

	public IndividualisedProductItem readProduct(long id) {
		logger.info("readProduct()");

		GenericCRUDExecutor<AbstractProduct> productCRUD = getCRUDExecutorFromWsContext();
		return (IndividualisedProductItem)productCRUD.readObject(id);
	}

	private GenericCRUDExecutor<AbstractProduct> getCRUDExecutorFromWsContext() {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);

		return (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
	}
}
