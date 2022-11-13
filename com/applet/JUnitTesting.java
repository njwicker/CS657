package com.applet;
import static org.junit.Assert.*;

import java.beans.PersistenceDelegate;

import org.junit.Test;


public class JUnitTesting 
{
    @Test
    public void testArrayNotNull()
    {
        final Particle[] particlesTest = new Particle[0];
        final Particle[] particlesTest2 = ParticleCanvas.getParticles();
        assertArrayEquals("Array Must be equal", particlesTest, particlesTest2);
    }

    @Test
    public void testSychronization()
    {
        Particle particle1 = new Particle(2, 5);
        Particle particle2 = new Particle(8, 9);
        Particle[] arr = new Particle[2];
        arr[0] = particle1;
        arr[1] = particle2;


        final Particle particle1pre = new Particle(0, 0);
        particle1pre.x =particle1.x;
        particle1pre.y = particle1.y;

        particle1.move();
        particle2.move();

        

        ParticleCanvas.setParticles(arr);

        assertNotEquals(particle1.x, particle1pre.x);
        assertNotEquals(particle1.y, particle1pre.y);
        assertEquals(arr.length, ParticleCanvas.getParticles().length);
    }

    @Test
    public void testStartandStop()
    {

    }

    @Test
    public void testChangingParticle()
    {

    }

}
