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
        assertEquals(Geldbetrag.get(3, 50), Geldbetrag.stringToGeldbetrag("3,50"));
        assertEquals(Geldbetrag.get(3, 50), Geldbetrag.stringToGeldbetrag("03,50"));
        assertEquals(Geldbetrag.get(3, 50), Geldbetrag.stringToGeldbetrag("000000003,50"));
        assertEquals(Geldbetrag.get(3, 50), Geldbetrag.stringToGeldbetrag("3,5"));
        
        assertEquals(Geldbetrag.get(0, 72), Geldbetrag.stringToGeldbetrag("0,72"));
        assertEquals(Geldbetrag.get(0, 72), Geldbetrag.stringToGeldbetrag(",72"));
        assertEquals(Geldbetrag.get(1, 0), Geldbetrag.stringToGeldbetrag("1,"));
        
        try
        {
            Geldbetrag.stringToGeldbetrag("3,501");
            fail("Exception wurde erwartet");
        }
        catch(NumberFormatException e)
        {
            
        }
        catch (Exception e)
        {
            fail("Andere Exception wurde erwartet");
        }
        
        try
        {
            Geldbetrag.stringToGeldbetrag("1234567891");
            fail("Exception wurde erwartet");
        }
        catch(NumberFormatException e)
        {
            
        }
        catch (Exception e)
        {
            fail("Andere Exception wurde erwartet");
        }
        
    }
    
    /**
     * Testet das Konvertieren eines Integers in einen Geldbetrag
     */
    @Test
    public void testeIntegerToGeldbetrag()
    {
        assertEquals(Geldbetrag.get(1, 43), Geldbetrag.integerToGeldbetrag(143));
    }
    
    /**
     * Testet ob ein Geldbetrag größer ist als ein anderer
     */
    @Test
    public void testeGreater()
    {
        assertTrue(_zweiEuroZehn.greater(_einEuroFuenfzig));
        assertFalse(_einEuroFuenfzig.greater(_zweiEuroZehn));
    }

    /**
     * Testet ob ein Geldbetrag kleiner ist als ein anderer
     */
    
    @Test
    public void testeLesser()
    {
        assertFalse(_zweiEuroZehn.lesser(_einEuroFuenfzig));
        assertTrue(_einEuroFuenfzig.lesser(_zweiEuroZehn));
    }
    
    /**
     * Testet die korrekte Implementation von Comparable
     */
    @Test
    public void testeComparableOperatore()
    {
    	assertEquals(1, _zweiEuroZehn.compareTo(_einEuroFuenfzig));
    	assertEquals(0, _einEuroFuenfzig.compareTo(_einEuroFuenfzig));
    	assertEquals(-1, _einEuroFuenfzig.compareTo(_zweiEuroZehn));
    }
}
