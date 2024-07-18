package com.kasarisute.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kasarisute.entitys.User;
import com.kasarisute.repositories.UserRepository;

@Component
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userString) throws UsernameNotFoundException {
        try {

            User user = userRepository.findByUid(Long.parseLong(userString));
            SecurityUser securityUser = new SecurityUser();

            securityUser.setMail(user.getMail());
            securityUser.setUid(user.getUid());
            securityUser.setPassword(user.getUserPw().getPassword());
            securityUser.setPermission(user.getPermission());
            securityUser.setRoles(user.getRoles());
            securityUser.setUserName(user.getUserName());
            securityUser.setUserCode(user.getUserCode());

            SecurityUserDetail securityUserDetail = new SecurityUserDetail(securityUser);

            return securityUserDetail;

        } catch (Exception e) {
            throw new UsernameNotFoundException("not found");
        }

    }

}
