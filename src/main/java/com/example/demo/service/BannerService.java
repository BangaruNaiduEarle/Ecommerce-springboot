package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BannerModel;
import com.example.demo.repo.BannerRepo;

@Service
public class BannerService {
	private final BannerRepo bannerRepo;

    // Inject BannerRepo using constructor injection
    @Autowired
    public BannerService(BannerRepo bannerRepo) {
        this.bannerRepo = bannerRepo;
    }

    public List<BannerModel> getAllBanners() {
        // Implement logic to fetch banners from the repository
        return bannerRepo.findAll();
    }
    public Optional<BannerModel> getBannerById(Long id) {
        return bannerRepo.findById(id);
    }
   
    public void setBannerLink(Long id, String bannerLink) {
        Optional<BannerModel> banner = bannerRepo.findById(id);
        banner.ifPresent(b -> {
            b.setBannerLink(bannerLink);
            bannerRepo.save(b);
        });
    }
	

}
