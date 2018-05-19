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

import com.xvr.entities.DepartmentEntity;

public class DepartmentEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DepartmentEntity AS o";

    private DepartmentEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DepartmentEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DepartmentEntity getEntity() {
        return myEntity;
    }

    public void setEntity(DepartmentEntity entity) {
        myEntity = entity;
    }

    // add new DepartmentEntity
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

        return "departmentEntityList";
    }

    // save edited DepartmentEntity
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
        return "departmentEntityList";
    }

    // delete DepartmentEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DepartmentEntity entity = getCurrentEntity();
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

        return "departmentEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DepartmentEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DepartmentEntity>(entities));
    }

    public String startCreate() {
        myEntity = new DepartmentEntity();
        return "createDepartmentEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDepartmentEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDepartmentEntity";
    }

    public DepartmentEntity getCurrentEntity() {
        DepartmentEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DepartmentEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DepartmentEntity entity = (DepartmentEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DepartmentEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DepartmentEntity entity = entityManager.find(DepartmentEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DepartmentEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DepartmentEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DepartmentEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DepartmentEntity> entities = (List<DepartmentEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
