/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.repository.impl;

import portal.dto.Payment;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import portal.dto.Authorities;
import portal.dto.Comment;
import portal.dto.Story;
import portal.dto.Users;
import portal.repository.StoryRepository;

/**
 *
 * @author laptop-02
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class StoryRepositoryImpl implements StoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Story> getAllNews() {
        String query = "Select s from Story s";
        
        return  entityManager.createQuery(query, Story.class).getResultList();  
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Story getNewByID(int newID) {
       return entityManager.find(Story.class, newID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addComment(Comment c) {
        entityManager.persist(c);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Comment> getAllNotVerifiedComments() {
        String query = "Select c from Comment c where Prihvacen = 0";
        
        return  entityManager.createQuery(query, Comment.class).getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void acceptCommentByID(int storyID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void declineCommentByID(int storyID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStory(Story story) {
        entityManager.merge(story);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addStory(Story story) {
        entityManager.persist(story);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Date getLastPayDateByUsername(String username) {
        String query = "Select p from Payment p where Username = :username";
        
        return  entityManager.createQuery(query, Payment.class).setParameter("username", username).getResultList().get(0).getLastPay();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updatePayment(String username) {
        String query = "Select p from Payment p where Username = :username";
        Payment p = entityManager.createQuery(query, Payment.class).setParameter("username", username).getResultList().get(0);
        Date datum = new Date(new java.util.Date().getTime());
        p.setLastPay(datum);
//        String query = "Update Payment p set lastPay = SYSTIMESTAMP where Username = :username";
//        entityManager.createQuery(query).setParameter("username", username);
        entityManager.merge(p);
    }
    
}
