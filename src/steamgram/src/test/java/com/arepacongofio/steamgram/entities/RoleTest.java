package com.arepacongofio.steamgram.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void enumValuesTest() {
        Role[] roles = Role.values();
        assertEquals(2, roles.length);
        assertEquals(Role.USER, Role.valueOf("USER"));
        assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
    }
}
