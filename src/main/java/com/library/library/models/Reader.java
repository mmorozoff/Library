package com.library.library.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name="readers")
public class Reader extends User{

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "reader")
    private Set<Act> acts = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Act> getActs() {
        return acts;
    }

    public void setActs(Set<Act> acts) {
        this.acts = acts;
    }
}

