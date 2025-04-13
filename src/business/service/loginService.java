package business.service;

import business.dao.login.loginDAO;

public class loginService {
    private loginDAO loginDao;
    public loginService() {
        this.loginDao = new loginDAO();
    }
    public boolean login(String email, String password) {
        return loginDao.login(email, password);
    }
}
