package book.book.service.impl;

import book.book.entity.User;
import book.book.mapper.UserMapper;
import book.book.repository.UserRepository;
import book.book.to.UserTo;
import book.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public List<UserTo> findAllUsers() {
		return this.userMapper.map2To(this.userRepository.findAll());
	}

	@Override
	public UserTo findUserByName(final String name) {
		final Optional<User> entity = this.userRepository.findUserByName(name);
		return entity.map(user -> this.userMapper.map2To(user))
				.orElse(null);
	}

}
