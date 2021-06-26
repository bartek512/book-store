package book.book.mapper;

import book.book.entity.User;
import book.book.to.UserTo;

import java.util.List;

public interface UserMapper {

	UserTo map2To(User user);

	User map2Entity(UserTo userTo);

	List<UserTo> map2To(List<User> userEntities);

	List<User> map2Entity(List<UserTo> userEntities);

}
