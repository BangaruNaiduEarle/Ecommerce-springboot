package com.example.demo.repo;

import org.springframework.stereotype.Repository;
import com.example.demo.model.BannerModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BannerRepo extends JpaRepository<BannerModel,Long>{
	
}
