package samuelesimeone.ProgettoU5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

@Entity
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
    @Id
    @GeneratedValue
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


    public User(String name, String surname, LocalDate age, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
    }

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
