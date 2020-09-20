package com.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.pojo.Users;
import com.project.service.intf.LoginServiceInf;
import com.project.util.AESEncryption;

@Component
public class JwtServiceDetails implements UserDetailsService {
	@Autowired
	private LoginServiceInf loginServiceIntf;
     @Autowired
     private BCryptPasswordEncoder encoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users users = loginServiceIntf.getUserByName(username);
			if (users == null) {
				throw new UsernameNotFoundException(username);
			}

			AESEncryption.init();

			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority s = new SimpleGrantedAuthority(users.getRole().getRoleName());
			list.add(s);
			return new User(users.getUserName(), encoder.encode(AESEncryption.getInstance().decode(users.getPassword())), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
