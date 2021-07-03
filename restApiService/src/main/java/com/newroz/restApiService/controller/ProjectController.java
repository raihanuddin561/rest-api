package com.newroz.restApiService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newroz.restApiService.model.ProjectInfo;
import com.newroz.restApiService.service.ProjectInfoService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectInfoService projectService;

	@GetMapping()
	public List<ProjectInfo> getProjectList() {
		return projectService.getProjectInfo();
	}

	@PostMapping()
	public ResponseEntity<ProjectInfo> insertProject(@RequestBody ProjectInfo body) {
		return new ResponseEntity<ProjectInfo>(projectService.saveProject(body), HttpStatus.OK);
	}

	@GetMapping("/:id")
	public ResponseEntity<ProjectInfo> getProjectById(@PathVariable String id) {
		ProjectInfo projectInfo = projectService.projectById(id);
		if(projectInfo!=null) {
		return new ResponseEntity<ProjectInfo> (projectService.projectById(id),HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping()
	public ResponseEntity<ProjectInfo> updateProject(@RequestBody ProjectInfo body){
		String id = body.getProjectId();		ProjectInfo pInfo = projectService.projectById(id);
		if(pInfo!=null) {
			return new ResponseEntity<ProjectInfo>(projectService.saveProject(body), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/:id")
	public ResponseEntity<ProjectInfo> deletProject(@PathVariable long id){
		return ResponseEntity.notFound().build();
	}

}
