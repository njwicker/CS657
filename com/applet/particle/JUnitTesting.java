package com.applet.particle;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class JUnitTesting 
{
    @Test
    public void testArrayNotNull()
    {
        final Particle[] particlesTest2 = ParticleCanvas.getParticles();
        assertNotEquals("Array Must be empty not null", null, particlesTest2);
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
        ParticleApplet applet = new ParticleApplet();
        applet.init();
        assertEquals(applet.getComponents().length, 1);
        applet.start();
        assertEquals(ParticleCanvas.getParticles().length, 10);
        assertEquals(applet.threads.length, 10);
        assertTrue(Arrays.stream(applet.threads).allMatch(Thread::isAlive));
        applet.stop();
        assertNull(applet.threads);
    }

    @Test
    public void testChangingParticle()//Particle canvas should not change or create any particles
    {
        Particle particle1 = new Particle(2, 5);
        Particle particle2 = new Particle(8, 9);
        Particle[] arr = new Particle[2];
        arr[0] = particle1;
        arr[1] = particle2;

        ParticleCanvas.setParticles(arr);

        assertEquals(arr.length, ParticleCanvas.getParticles().length); //Shows this method does not change the length
        assertArrayEquals(arr, ParticleCanvas.getParticles()); // shows Particle canvas does not change any values

        assertThrows(IllegalArgumentException.class, () -> ParticleCanvas.setParticles(null));

    }

}
