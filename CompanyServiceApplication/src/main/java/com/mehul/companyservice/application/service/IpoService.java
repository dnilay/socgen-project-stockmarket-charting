package com.mehul.companyservice.application.service;

import java.util.List;

import com.mehul.companyservice.application.dto.IpoDto;

public interface IpoService 
{
	public List<IpoDto> findAll();
	public IpoDto findById(String id);
	public IpoDto save(IpoDto ipoDto);
	public IpoDto update(IpoDto ipoDto);
	public void deleteById(String id);
}
