package fr.litopia.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "LesModeles")
public class Modele {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "modele", orphanRemoval = true)
    private Set<Velo> velos = new LinkedHashSet<>();

    public Set<Velo> getVelos() {
        return velos;
    }

    public void setVelos(Set<Velo> velos) {
        this.velos = velos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}