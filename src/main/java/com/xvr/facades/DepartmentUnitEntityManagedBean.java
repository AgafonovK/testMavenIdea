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

import com.xvr.entities.DepartmentUnitEntity;

public class DepartmentUnitEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM DepartmentUnitEntity AS o";

    private DepartmentUnitEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public DepartmentUnitEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public DepartmentUnitEntity getEntity() {
        return myEntity;
    }

    public void setEntity(DepartmentUnitEntity entity) {
        myEntity = entity;
    }

    // add new DepartmentUnitEntity
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

        return "departmentUnitEntityList";
    }

    // save edited DepartmentUnitEntity
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
        return "departmentUnitEntityList";
    }

    // delete DepartmentUnitEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DepartmentUnitEntity entity = getCurrentEntity();
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

        return "departmentUnitEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<DepartmentUnitEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<DepartmentUnitEntity>(entities));
    }

    public String startCreate() {
        myEntity = new DepartmentUnitEntity();
        return "createDepartmentUnitEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewDepartmentUnitEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editDepartmentUnitEntity";
    }

    public DepartmentUnitEntity getCurrentEntity() {
        DepartmentUnitEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public DepartmentUnitEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        DepartmentUnitEntity entity = (DepartmentUnitEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public DepartmentUnitEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        DepartmentUnitEntity entity = entityManager.find(DepartmentUnitEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<DepartmentUnitEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (DepartmentUnitEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<DepartmentUnitEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<DepartmentUnitEntity> entities = (List<DepartmentUnitEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
