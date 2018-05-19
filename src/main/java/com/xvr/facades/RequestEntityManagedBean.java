package com.xvr.facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.xvr.entities.RequestEntity;

public class RequestEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM RequestEntity AS o";

    private RequestEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public RequestEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public RequestEntity getEntity() {
        return myEntity;
    }

    public void setEntity(RequestEntity entity) {
        myEntity = entity;
    }

    // add new RequestEntity
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "requestEntityList";
    }

    // save edited RequestEntity
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            myEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "requestEntityList";
    }

    // delete RequestEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            RequestEntity entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "requestEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<RequestEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<RequestEntity>(entities));
    }

    public String startCreate() {
        myEntity = new RequestEntity();
        return "createRequestEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewRequestEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editRequestEntity";
    }

    public RequestEntity getCurrentEntity() {
        RequestEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public RequestEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        RequestEntity entity = (RequestEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public RequestEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        RequestEntity entity = entityManager.find(RequestEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<RequestEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (RequestEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<RequestEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<RequestEntity> entities = (List<RequestEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
