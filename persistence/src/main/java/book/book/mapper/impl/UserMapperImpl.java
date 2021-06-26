package book.book.mapper.impl;

import book.book.entity.User;
import book.book.mapper.UserMapper;
import book.book.to.UserTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserTo map2To(final User user) {
		if (user != null) {
			return new UserTo(user.getId(), user.getUserName(), user.getPassword());
		}
		return null;
	}

	@Override
	public User map2Entity(final UserTo userTo) {
		if (userTo != null) {
			return new User(userTo.getId(), userTo.getUserName(), userTo.getPassword());
		}
		return null;
	}

	@Override
	public List<UserTo> map2To(final List<User> userEntities) {
		return userEntities.stream()
				.map(this::map2To)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> map2Entity(final List<UserTo> userEntities) {
		return userEntities.stream()
				.map(this::map2Entity)
				.collect(Collectors.toList());
	}

}
