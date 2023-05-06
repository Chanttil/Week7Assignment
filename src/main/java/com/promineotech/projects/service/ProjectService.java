package com.promineotech.projects.service;

import com.promineotech.projects.dao.ProjectDao;
import com.promineotech.projects.entity.Project;


public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();
    public Project addProject(Project project) {                    //Step 14
       return projectDao.insertProject(project);
    }

}
