package com.project.cmsShoppingCart.models;

import com.project.cmsShoppingCart.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
