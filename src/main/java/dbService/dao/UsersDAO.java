package dbService.dao;

import dbService.dataSets.UsersProfileDataSet;
//import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersProfileDataSet get(long id) throws HibernateException {
        return (UsersProfileDataSet) session.get(UsersProfileDataSet.class, id);
    }


    public UsersProfileDataSet getByLogin(String login) throws HibernateException {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsersProfileDataSet> cq = cb.createQuery(UsersProfileDataSet.class);

        Root<UsersProfileDataSet> usersDataSetRoot = cq.from(UsersProfileDataSet.class);

        cq.where(cb.equal(usersDataSetRoot.get("login"), login));

        Query<UsersProfileDataSet> q = session.createQuery(cq);

        try {
            return q.getSingleResult();
        } catch (NoResultException noRes) {
            return null;
        }

    }

    public long insertUser(String login, String pass, String email) throws HibernateException {
        return (Long) session.save(new UsersProfileDataSet(login, pass, email));
    }


}
