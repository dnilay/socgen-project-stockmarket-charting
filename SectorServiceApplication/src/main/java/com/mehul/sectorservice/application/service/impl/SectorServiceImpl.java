package com.mehul.sectorservice.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehul.sectorservice.application.dao.SectorRepository;
import com.mehul.sectorservice.application.dto.CompanyDto;
import com.mehul.sectorservice.application.dto.SectorDto;
import com.mehul.sectorservice.application.mapper.CompanyMapper;
import com.mehul.sectorservice.application.mapper.SectorMapper;
import com.mehul.sectorservice.application.model.Sector;
import com.mehul.sectorservice.application.service.SectorService;

@Service
public class SectorServiceImpl implements SectorService
{
	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired
	private SectorMapper sectorMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public SectorDto save(SectorDto sectorDto) 
	{
		Sector sector = sectorMapper.toSector(sectorDto);
		sector = sectorRepository.save(sector);
		return sectorMapper.toSectorDto(sector);
	}

	@Override
	public List<SectorDto> findAll() 
	{
		List<Sector> sectors = sectorRepository.findAll();
		return sectorMapper.toSectorDtos(sectors);
	}

	@Override
	public SectorDto findById(String id) 
	{
		Optional<Sector> sector = sectorRepository.findById(id);
		if(!sector.isPresent())
			return null;
		return sectorMapper.toSectorDto(sector.get());
	}
	
	@Override
	public void deleteById(String id) {
		sectorRepository.deleteById(id);
	}

	@Override
	public List<CompanyDto> getCompanies(String id) 
	{
		Optional<Sector> sector = sectorRepository.findById(id);
		if(!sector.isPresent())
			return null;
		return companyMapper.toCompanyDtos(sector.get().getCompanies());
	}
	
	/* Feign Client Service */

	@Override
	public SectorDto addCompanyToSector(String sectorName, CompanyDto companyDto) 
	{
		Sector sector = sectorRepository.findByName(sectorName);
		if(sector == null)
			return null;
		sector.getCompanies().add(companyMapper.toCompany(companyDto));
		sector = sectorRepository.save(sector);
		return sectorMapper.toSectorDto(sector);
	}
}
