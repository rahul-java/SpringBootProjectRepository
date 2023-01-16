package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}
