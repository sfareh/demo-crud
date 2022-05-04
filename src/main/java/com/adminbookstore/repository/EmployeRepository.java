package com.adminbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminbookstore.model.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
