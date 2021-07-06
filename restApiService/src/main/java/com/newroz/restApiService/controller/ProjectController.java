package com.newroz.restApiService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.newroz.restApiService.utility.Utils;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private Utils utils;
	@Autowired
	private ProjectInfoService projectService;
	@GetMapping("/raihan")
	public String getName() {
		return "Raihan's project";
	}
	@GetMapping()
	public List<ProjectInfo> getProjectList() {
		return projectService.getProjectInfo();
	}

	@PostMapping()
	public ResponseEntity<ProjectInfo> insertProject(@RequestBody ProjectInfo body) {
		String projectId = utils.generateUserId(5);
		body.setProjectId(projectId);
		return new ResponseEntity<ProjectInfo>(projectService.saveProject(body), HttpStatus.OK);
	}

	@GetMapping(path="/{id}")
	public ResponseEntity<ProjectInfo> getProjectById(@PathVariable String id) {
		System.out.println("Printing pathvariable: "+id);
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
	
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> deletProject(@PathVariable String id){
		ProjectInfo pInfo = projectService.projectById(id);
		if(pInfo!=null) {
			projectService.deleteByProjectId(pInfo);
			return ResponseEntity.ok("Entity deleted");
		}
		return ResponseEntity.notFound().build();
	}

}
