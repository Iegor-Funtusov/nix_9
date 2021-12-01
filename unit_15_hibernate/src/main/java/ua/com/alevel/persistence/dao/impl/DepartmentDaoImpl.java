package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Department entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Department entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
//        session.flush();
//        session.detach(entity);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Department d where d.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(d.id) from Department d where d.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Department findById(Long id) {
        return sessionFactory.getCurrentSession().find(Department.class, id);
    }

    @Override
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> from = criteriaQuery.from(Department.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        List<Department> departments = sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Department> response = new DataTableResponse<>();
        response.setItems(departments);
        response.setOrder(request.getOrder());
        response.setSort(request.getSort());
        response.setCurrentPage(request.getPage());
        response.setPageSize(request.getSize());

        return response;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(d.id) from Department d");
        return (Long) query.getSingleResult();
    }
}
