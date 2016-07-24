package ru.parsentev.servlets;

import org.junit.Test;
import ru.parsentev.store.UserCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * TODO: comment
 *
 * @author vlad
 * @since 24.07.2016
 */
public class UserCRUDServletTest {

    final UserCache cache = UserCache.getInstance();

    @Test
    public void createUser() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("email")).thenReturn("test");

        assertTrue(cache.values().isEmpty());

        new UserCreateServlet().doPost(request, response);

        // проверка вызова метода хотябы один раз
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("email");
        assertFalse(cache.values().isEmpty());

        cache.delete(cache.findByLogin("test").getId());
    }
}