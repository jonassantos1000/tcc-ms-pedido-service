package com.usp.esalq.tcc.utils;

import com.usp.esalq.tcc.domain.entity.UsuarioEntity;

public class SecurityContextUtils {

    private static final ThreadLocal<UsuarioEntity> USER = new ThreadLocal<>();

    public static void setUsuario(UsuarioEntity usuario) {
    	USER.set(usuario);
    }

    public static UsuarioEntity getUsuario() {
        return USER.get();
    }

    public static void clear() {
    	USER.remove();
    }
}
