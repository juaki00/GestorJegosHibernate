package com.example.gestorjegoshibernate;

import com.example.gestorjegoshibernate.domain.juego.Game;
import com.example.gestorjegoshibernate.domain.usuario.User;
import lombok.Getter;
import lombok.Setter;


public class Session {
    @Getter
    @Setter
    private static User currentUser;

    @Getter
    @Setter
    private static Game currentGame;

}
