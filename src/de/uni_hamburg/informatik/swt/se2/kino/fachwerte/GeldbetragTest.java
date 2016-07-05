package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag _einEuroFuenfzig = Geldbetrag.get(1, 50);
    Geldbetrag _zweiEuroZehn = Geldbetrag.get(2, 10);
    
    
    @Test
    public void testeGet()
    {
        
    }
    
    /**
     * Schaut, ob die String Repräsentation korrekt ist.
     */
    @Test
    public void testeStringRepraesentation()
    {
        assertEquals("01,50", _einEuroFuenfzig.toString());
        assertEquals("01,03", Geldbetrag.get(1, 3).toString()); // schaut, dass auch die null vor unter 10ct Beträgen eingefügt wird
    }
    
    /**
     * Testet Addieren zweier Geldbeträge
     */
    @Test
    public void testeAddieren()
    {
        assertEquals(Geldbetrag.get(3, 60), _einEuroFuenfzig.plus(_zweiEuroZehn));
        assertEquals(Geldbetrag.get(3, 00), _einEuroFuenfzig.plus(_einEuroFuenfzig));
    }
    
    /**
     * Testet das Subtrahieren zweier Geldbeträge
     */
    @Test
    public void testeSubtrahieren()
    {
        assertEquals(Geldbetrag.get(0, 60), _zweiEuroZehn.minus(_einEuroFuenfzig));
        assertEquals(Geldbetrag.get(0, 60), _einEuroFuenfzig.minus(_zweiEuroZehn));
    }
    
    /**
     * Testet das Multiplizieren zweier Geldbeträge
     */
    @Test
    public void testeMultiplizierenMitEinerZahl()
    {
        assertEquals(Geldbetrag.get(3, 0), _einEuroFuenfzig.mal(2));
        assertEquals(Geldbetrag.get(3, 0), _einEuroFuenfzig.mal(-2));
    }
    
    /**
     * Testet das Konvertgieren eines Strings in einen Geldbetrag
     */
    @Test
    public void testeStringToGeldbetrag()
    {
        assertEquals(Geldbetrag.get(3, 50), Geldbetrag.stringToGeldbetrag("03,50"));
    }
    
    /**
     * Testet das Konvertieren eines Integers in einen Geldbetrag
     */
    @Test
    public void testeIntegerToGeldbetrag()
    {
        assertEquals(Geldbetrag.get(1, 43), Geldbetrag.integerToGeldbetrag(143));
    }

}
