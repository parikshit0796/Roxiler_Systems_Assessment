package com.storeratingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeratingapp.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{

}
