package sk.markot.assignment.service;

import sk.markot.assignment.dao.SUsersDao;
import sk.markot.assignment.entity.SUser;

public class SUserService {

    private final SUsersDao dao;

    public SUserService(SUsersDao dao) {
        this.dao = dao;
    }

    public void add(SUser user) {
        dao.add(user);
        System.out.println("Add user: " + user);
    }

    public void deleteAll() {
        dao.deleteAll();
        System.out.println("All users deleted");
    }

    public void printAll() {
        System.out.println("All users: " + dao.findAll());
    }

    public int count() {
        return dao.findAll().size();
    }
}
