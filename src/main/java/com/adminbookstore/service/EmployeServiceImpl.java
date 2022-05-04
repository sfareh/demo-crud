package com.adminbookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.adminbookstore.model.Employe;
import com.adminbookstore.repository.EmployeRepository;

@Service
public class EmployeServiceImpl implements EmployeService {
	@Autowired
	private EmployeRepository employeRepository;

	@Override
	public List<Employe> getAllEmployes() {

		return employeRepository.findAll();
	}

	@Override
	public void saveEmploye(Employe employe) {
		employeRepository.save(employe);
	}

	@Override
	public Employe getEmployeById(Long id) {
		Employe employe = employeRepository.getById(id);

		if (employe == null) {
			throw new RuntimeException("Employe not found for id :: " + id);
		}

		return employe;
	}

	@Override
	public void deleteEmploye(Long id) {
		employeRepository.deleteById(id);
	}

	@Override
	public Page<Employe> getEmployePagination(int pageNumber, int rowSize, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending()
				: Sort.by(field).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, rowSize, sort);
		return employeRepository.findAll(pageable);
	}

}
