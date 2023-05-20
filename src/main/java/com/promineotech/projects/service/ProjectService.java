package com.promineotech.projects.service;

import com.promineotech.projects.dao.ProjectDao;
import com.promineotech.projects.entity.Project;
import com.promineotech.projects.exception.DbException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();
    public Project addProject(Project project) {                    //Step 14

        return projectDao.insertProject(project);
    }

    public List<Project> fetchAllProjects() {                   //w10.4
        return projectDao.fetchAllProjects();

    }

    public Project fetchProjectById(Integer projectId) {            //w10
        Optional<Project> op = projectDao.fetchProjectById(projectId);

        return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
                "Project with project ID=" + projectId + "does not exist."));
    }

    public void modifyProjectDetails(Project project) {                  //checks if project exist
        if (!projectDao.modifyProjectDetails(project)) {
            throw new DbException("Project with ID="
                    + project.getProjectId() + " does not exist");
        }
    }

    public void deleteProject(Integer projectId) {                      //checks if project exist
        if (!projectDao.deleteProject(projectId)) {
            throw new DbException("Project with ID=" + projectId + " does not exist.");
        }
    }
}
