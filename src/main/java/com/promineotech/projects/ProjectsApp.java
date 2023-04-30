package com.promineotech.projects;

import com.promineotech.projects.dao.DbConnection;

public class ProjectsApp {
    public static void main(String[] args) {

        DbConnection.getConnection();
    }
}
