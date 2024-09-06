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
    	  String[] roles = user.isAdmin() ?
                  new String[]{"ADMIN", "USER"} : new String[]{"USER"};
    	if(roles.length > 0)
    		return AuthorityUtils.createAuthorityList("ROLE_"+roles[0], "ROLE_"+roles[1]);
    	else
    		return AuthorityUtils.createAuthorityList("ROLE_"+roles[0]);
    	
    
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
