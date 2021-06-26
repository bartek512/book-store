package book.book.service.impl;

import book.book.entity.UserRole;
import book.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        book.book.entity.User user = userRepository.findUserByName(username).orElseThrow();
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(book.book.entity.User user) {
        return User.withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(getAuthorities(user.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPrivileges().stream().map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }
}
