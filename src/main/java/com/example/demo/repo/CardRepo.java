package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CardModel;
@Repository
public interface CardRepo extends JpaRepository<CardModel, Long> {

}
