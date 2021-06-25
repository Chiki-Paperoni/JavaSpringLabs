package com.example.demo.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<OrderEntity,Long> {
    List<OrderEntity> findByUsername(String username);
}
