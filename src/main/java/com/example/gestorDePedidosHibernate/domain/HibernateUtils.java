package com.example.gestorDePedidosHibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtils {
    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con exito!");
        } catch(Exception e){
            log.severe("Error al crear SessionFactory");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
