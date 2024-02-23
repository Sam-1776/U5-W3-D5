package samuelesimeone.ProgettoU5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({
        "password",
        "role",
        "enabled",
        "authorities",
        "username",
        "accountNonLocked",
        "accountNonExpired",
        "credentialsNonExpired"})
public class User implements UserDetails {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate age;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

//    @JsonIgnore
//    @OneToMany(mappedBy = "persona")
//    private List<Partecipazione> partecipaziones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
