package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TacheRessource {

    @EmbeddedId
    private TacheRessourceId id = new TacheRessourceId();

    @ManyToOne
    @MapsId("tacheId")
    @JoinColumn(name = "tache_id")
    @JsonIgnore
    private Tache tache;

    @ManyToOne
    @MapsId("ressourceId")
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    private Double cout;

    private Boolean disponibilite;

    public TacheRessource(Tache tache, Ressource ressource, Double cout, Boolean disponibilite) {
        this.tache = tache;
        this.ressource = ressource;
        this.cout = cout;
        this.disponibilite = disponibilite;
        this.id = new TacheRessourceId(tache.getId(), ressource.getId());
    }
}
