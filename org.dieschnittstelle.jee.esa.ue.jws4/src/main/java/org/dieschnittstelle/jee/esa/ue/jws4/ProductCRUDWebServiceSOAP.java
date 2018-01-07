package org.dieschnittstelle.jee.esa.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", serviceName = "ProductCRUDWebService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@XmlSeeAlso(IndividualisedProductItem.class)
public class ProductCRUDWebServiceSOAP {
	protected static Logger logger = Logger
							.getLogger(ProductCRUDWebServiceSOAP.class);
	@Resource
	private WebServiceContext wscontext;

	public ProductCRUDWebServiceSOAP() {
		logger.info("<constructor>");
	}
	@PostConstruct
	@WebMethod(exclude = true)
	public void initialiseContext() {
		logger.info("@PostConstruct(ProductCRUDWebServiceSOAP): the wscontext is: " + wscontext);

		// we cannot read out any context attributes (ServletContext,
		// HttpServletRequest) from the WebServiceContext as this is only
		// allowed from a thread that actually handles a particular request to a
		// service operation
//		wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
	}
	@WebMethod
	public List<AbstractProduct> readAllProducts() {
		logger.info("readAllProducts()");

		logger.info("readAllProducts(): I am: " + this);

		// we obtain the servlet context from the wscontext
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		logger.info("readAllProducts(): servlet context is: " + ctx);
		// we also read out the http request
		HttpServletRequest httpRequest = (HttpServletRequest) wscontext
				.getMessageContext().get(MessageContext.SERVLET_REQUEST);
		logger.info("readAllProducts(): servlet request is: " + httpRequest);

		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		logger.info("readAllProducts(): read productCRUD from servletContext: "
				+ productCRUD);

		// check that more than one requests is handled by the same instance of this class simulataneously
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return productCRUD.readAllObjects();
	}

	@WebMethod
	public AbstractProduct createProduct(AbstractProduct product) {
		// obtain the CRUD executor from the servlet context
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");

		return (IndividualisedProductItem) productCRUD.createObject(product);
	}
	@WebMethod
	public AbstractProduct updateProduct(AbstractProduct update) {
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		AbstractProduct product = productCRUD.updateObject(update);
		return product;
	}

	@WebMethod
	public boolean deleteProduct(long id) {
		// obtain the CRUD executor from the servlet context
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");

		return productCRUD.deleteObject(id);
	}

	@WebMethod
	public IndividualisedProductItem readProduct(long id) {
		// obtain the CRUD executor from the servlet context
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return (IndividualisedProductItem) productCRUD.readObject(id);
	}

}
