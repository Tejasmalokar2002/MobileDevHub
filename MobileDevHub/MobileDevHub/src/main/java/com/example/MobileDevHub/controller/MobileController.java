package com.example.MobileDevHub.controller;

import com.example.MobileDevHub.dto.MobileDTO;
import com.example.MobileDevHub.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @GetMapping("/get")
    public List<MobileDTO> getAllMobiles() {
        return mobileService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MobileDTO> getMobileById(@PathVariable Long id) {
        return mobileService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<MobileDTO> createMobile(@RequestBody MobileDTO mobileDTO) {
        if (mobileDTO.getManufacturingDate() == null || mobileDTO.getReleaseDate() == null) {
            return ResponseEntity.badRequest().body(null);  // Handle null values appropriately
        }
        MobileDTO savedMobile = mobileService.saveMobile(mobileDTO);
        return ResponseEntity.ok(savedMobile);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<MobileDTO> updateMobile(@PathVariable Long id, @RequestBody MobileDTO mobileDTO) {
        return mobileService.update(id, mobileDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        if (mobileService.findById(id).isPresent()) {
            mobileService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
