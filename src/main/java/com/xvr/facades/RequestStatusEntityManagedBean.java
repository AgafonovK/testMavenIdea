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

import com.xvr.entities.RequestStatusEntity;

public class RequestStatusEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM RequestStatusEntity AS o";

    private RequestStatusEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public RequestStatusEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public RequestStatusEntity getEntity() {
        return myEntity;
    }

    public void setEntity(RequestStatusEntity entity) {
        myEntity = entity;
    }

    // add new RequestStatusEntity
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

        return "requestStatusEntityList";
    }

    // save edited RequestStatusEntity
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
        return "requestStatusEntityList";
    }

    // delete RequestStatusEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            RequestStatusEntity entity = getCurrentEntity();
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

        return "requestStatusEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<RequestStatusEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<RequestStatusEntity>(entities));
    }

    public String startCreate() {
        myEntity = new RequestStatusEntity();
        return "createRequestStatusEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewRequestStatusEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editRequestStatusEntity";
    }

    public RequestStatusEntity getCurrentEntity() {
        RequestStatusEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public RequestStatusEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        RequestStatusEntity entity = (RequestStatusEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public RequestStatusEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        RequestStatusEntity entity = entityManager.find(RequestStatusEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<RequestStatusEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (RequestStatusEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<RequestStatusEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<RequestStatusEntity> entities = (List<RequestStatusEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
