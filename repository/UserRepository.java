package com.example.demo.repository;

import com.example.demo.domain.User;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // Create, Update
    public void save(User user) {
        em.persist(user);
    }

    // Delete
    public void remove(Long id) {
        em.remove(findById(id));
    }

    // Read
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public Optional<User> findByName(String findName) {
    	Optional<User> user = null;
    	try {
            user = Optional.ofNullable(em.createQuery("SELECT u FROM User u WHERE u.name = :findName", User.class)
                    .setParameter("findName", findName)
                    .getSingleResult());    		
    	}catch (NoResultException e) {
    		user = Optional.empty();
    	}finally {
    		return user;
    	}
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}