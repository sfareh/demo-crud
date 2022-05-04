package com.adminbookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.adminbookstore.model.Employe;

public interface EmployeService {
	/**
	 * 
	 * @return a List of all employes
	 */
	List<Employe> getAllEmployes();

	/**
	 * 
	 * @param employe to save in the DB
	 */
	void saveEmploye(Employe employe);

	/**
	 * 
	 * @param id of the employe to retrieve from db
	 * @return return the employe object
	 */
	Employe getEmployeById(Long id);

	/**
	 * 
	 * @param id of the employe to delete
	 */
	void deleteEmploye(Long id);

	/**
	 * 
	 * @param pageNumber
	 * @param rowNumber
	 * @return all employe with paginantion
	 */
	Page<Employe> getEmployePagination(int pageNumber, int rowSize, String field, String direction);

}
