package com.example.servicepay.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.servicepay.entities.UserModel;

import lombok.Data;

@Data
public class CurrentUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserModel user;

    public CurrentUser(UserModel user) {
        this.user = user;
     
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
    	String[] userRoles = new String[user.getRoles().size() ];
    	
    	for(int i = 0; i < userRoles.length; i++) {
    		userRoles[i] = "ROLE_"+user.getRoles().get(i).getName();
    	}
    	return AuthorityUtils.createAuthorityList(userRoles);
    	
    }
    
    @Override
    public String getPassword() {
      return this.user.getPassword();
    }

    @Override
    public String getUsername() {
      return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

}
