package book.book.service;

import book.book.to.UserTo;

import java.util.List;

public interface UserService {

	List<UserTo> findAllUsers();

	UserTo findUserByName(String name);

}
