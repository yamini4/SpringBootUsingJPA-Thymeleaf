package com.project.cmsShoppingCart.models;

import com.project.cmsShoppingCart.models.data.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsername(String username);

}
