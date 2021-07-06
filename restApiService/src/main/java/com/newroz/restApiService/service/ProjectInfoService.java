package com.newroz.restApiService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.newroz.restApiService.model.ProjectInfo;

public interface ProjectInfoService {
	public List<ProjectInfo> getProjectInfo();
	public ProjectInfo saveProject(ProjectInfo projectInfo);
	public ProjectInfo projectById(String id);
	public void deleteByProjectId(ProjectInfo pInfo);

}
