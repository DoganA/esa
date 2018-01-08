package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

//TODO make CRUDs generic
@Stateless
public abstract class GenericCRUDStateless {

    private EntityManager em;

}
