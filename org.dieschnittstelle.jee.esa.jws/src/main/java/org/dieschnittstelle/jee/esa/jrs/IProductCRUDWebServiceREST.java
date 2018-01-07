package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@Path("/products")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@WebService(serviceName = "ProductCRUDWebService", targetNamespace = "org.dieschnittstelle.jee.esa.jws")
interface IProductCRUDWebServiceREST {
    @GET
    public List<IndividualisedProductItem> readAllProducts();

    @POST
    public IndividualisedProductItem createProduct(IndividualisedProductItem touchpoint);

    @DELETE
    @Path("/{productId}")
    public boolean deleteProduct(@PathParam("productId") long id);

    @GET
    @Path("/{productId}")
    public IndividualisedProductItem readProduct(@PathParam("productId") long id);


    @PUT
    public IndividualisedProductItem updateProduct(IndividualisedProductItem touchpoint);
}
