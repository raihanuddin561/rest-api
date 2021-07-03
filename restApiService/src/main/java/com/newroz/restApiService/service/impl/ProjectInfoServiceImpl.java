package com.newroz.restApiService.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newroz.restApiService.model.ProjectInfo;
import com.newroz.restApiService.repository.ProjectRepository;
import com.newroz.restApiService.service.ProjectInfoService;
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
	@Autowired
	private ProjectRepository projectRepo;

	@Override
	public List<ProjectInfo> getProjectInfo() {
		
		return projectRepo.findAll();
	}

	@Override
	public ProjectInfo saveProject(ProjectInfo projectInfo) {
		return projectRepo.save(projectInfo);
	}

	@Override
	public ProjectInfo projectById(String id) {
		
		return projectRepo.findByProjectId(id);
	}

	

}
