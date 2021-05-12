package entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author root
 */
public class MyUser implements UserDetails {
    private String username;
    private String password;
    private String role;

    public MyUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public MyUser(String username, String password){
        this(username, password, "USER");
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.username.equals("admin")){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", role=" + role + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(username, myUser.username) && Objects.equals(password, myUser.password) && Objects.equals(role, myUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }
}
