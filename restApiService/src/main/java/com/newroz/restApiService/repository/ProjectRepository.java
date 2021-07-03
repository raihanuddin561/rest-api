package com.newroz.restApiService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newroz.restApiService.model.ProjectInfo;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectInfo, Long> {
	public ProjectInfo findByProjectId(String projectId);
}
