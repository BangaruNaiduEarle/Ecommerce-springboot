package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BannerModel;
import com.example.demo.service.BannerService;

@RestController
@RequestMapping
public class BannerController {
	private final BannerService bannerService;

    // Inject BannerService using constructor injection
    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/banner/getAllBanners")
    public ResponseEntity<List<BannerModel>> getAllBanners() {
        try {
            List<BannerModel> banners = bannerService.getAllBanners();

            // Append the production link to each banner
            banners.forEach(banner -> {
                banner.setBannerLink("https://--------/-----" + banner.getBannerName());
            });

            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception ex) {
            // Log the exception or handle it accordingly
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/banner/getBannerById/{id}")
    public ResponseEntity<BannerModel> getBannerById(@PathVariable Long id) {
        try {
            Optional<BannerModel> banner = bannerService.getBannerById(id);

            if (banner.isPresent()) {
                // Append the production link to the banner
                banner.get().setBannerLink("https://----------/---" + banner.get().getBannerName());

                return new ResponseEntity<>(banner.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Log the exception or handle it accordingly
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
