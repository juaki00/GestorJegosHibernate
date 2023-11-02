package com.example.gestorjegoshibernate.domain.usuario;

import com.example.gestorjegoshibernate.domain.juego.Game;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario")
    private String username;
    @Column(name = "contraseña")
    private String password;

//    @OneToMany(mappedBy = "user")
//    private ArrayList<Game> games = new ArrayList<Game>(0);

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}