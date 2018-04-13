/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.singleton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ThinkPad
 */
public class PersonTest {

    /**
     * Test of instance method, of class Person.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        Person p1 = Person.instance();
        Person p2 = Person.instance();
        assertEquals(p1, p2);
    }

}
