package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Ein Fachwert zur Repräsentation von Geldbeträgen für das Kinosystem
 * 
 * @author Balthasar Spanner
 *
 */
public final class Geldbetrag
{
    private final int _euro;
    private final int _cent;

    /**
     * Initialisiert ein Geldbetragsobjekt
     * 
     * @param euro
     *            Der Eurowert
     * @param cent
     *            Der Centwert
     * @require cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer"
     */
    private Geldbetrag(int euro, int cent)
    {
        assert cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer";

        _euro = euro;
        _cent = cent;
        
    }

    /**
     * ÷ffentliche Schnittstelle für den Fachwert
     * 
     * @param euro
     *            Der Eurowert
     * @param cent
     *            Der Centwert
     * @return Ein Geldbetragsobjekt
     * 
     * @require cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer"
     */
    public static Geldbetrag get(int euro, int cent)
    {
        assert cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer";

        return new Geldbetrag(euro, cent);
    }

    /**
     * Addiert zwei Geldbeträge
     * 
     * @param other
     *            Der zu addierende Geldbetrag
     * @return Ein neues Geldbetragsobjekt
     * @require other != null
     */
    public Geldbetrag plus(Geldbetrag other)
    {
        assert other != null : "Vorbedingung verletzt: Der zu addierende Betrag darf nicht null sein";

        int tempCents = _cent + other._cent;
        int tempEuro = tempCents / 100;
        tempCents %= 100;
        tempEuro += _euro + other._euro;

        return Geldbetrag.get(tempEuro, tempCents);
    }

    /**
     * Subtrahiert zwei Geldbeträge
     * 
     * @param other
     *            Der zu Subtrahierende Geldbetrag
     * @return Ein neues Geldbetragsobjekt
     * @require other != null
     */
    public Geldbetrag minus(Geldbetrag other)
    {
        assert other != null : "Vorbedingung verletzt";

        int betragEins = (_euro * 100) + _cent;
        System.out.println(betragEins);
        int betragZwei = (other._euro * 100) + other._cent;
        System.out.println(betragZwei);
        int ergebniss = Math.abs(betragEins - betragZwei);
        return integerToGeldbetrag(ergebniss);

    }

    /**
     * Multipliziert einen Geldbetrag mit einer Zahl
     * 
     * @param other
     *            Der Multiplikator
     * @return Ein neues Geldbetragsobjekt
     * @require multiplikator != 0
     */
    public Geldbetrag mal(int multiplikator)
    {
        assert multiplikator != 0 : "Vorbedingung verletzt: null";
        
        int tempCent = Math.abs(_cent * multiplikator);
        int tempEuro = tempCent / 100;
        tempCent %= 100;
        tempEuro += Math.abs(_euro * multiplikator);

        return Geldbetrag.get(tempEuro, tempCent);
    }

    /**
     * Konvertiert einen String zu einem Geldbetrag
     * 
     * @param betrag
     *            Der zu konvertierende String
     * @return Ein neues Geldbetragsobjekt
     * @require betrag != null
     */

    private final static Pattern _pattern = Pattern
        .compile("0*?([1-9][0-9]{0,8})?(,([0-9][0-9]?))?");

    public static Geldbetrag stringToGeldbetrag(String betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: null";
        
        Matcher matcher = _pattern.matcher(betrag);

        int tempEuros = 0;
        int tempCents = 0;

        if (matcher.matches())
        {

            if (matcher.group(1) != null)
            {
                tempEuros = Integer.parseInt(matcher.group(1));
            }

            if (matcher.group(3) != null)
            {
                if (matcher.group(3)
                    .startsWith("0")
                        && matcher.group(3)
                            .length() > 1)
                {
                    tempCents = Integer.parseInt(matcher.group(3)
                        .substring(1));
                }
                else if (matcher.group(3)
                    .length() > 1)
                {
                    tempCents = Integer.parseInt(matcher.group(3));
                }
                else
                {
                    tempCents = (Integer.parseInt(matcher.group(3)) * 10);
                }
            }

        }
        return get(tempEuros, tempCents);
    }

    /**
     * Konvertiert einen Integer zu einem Geldbetrag
     * 
     * @param betrag
     *            Der zu konvertierende Integer
     * @return Ein neues Geldbetragsobjekt
     * @require betrag != 0
     */
    public static Geldbetrag integerToGeldbetrag(int betrag)
    {
        assert betrag != 0 : "Vorbedingung verletzt: null";
        
        return Geldbetrag.get(betrag / 100, betrag % 100);
    }

    /**
     * Überprüft, ob der andere Geldbetrag größer ist
     * 
     * @param other
     *            Der zu testende Betrag
     * @return true oder false
     * @require other != null
     */
    public boolean greater(Geldbetrag other)
    {
        assert other != null : "Vorbedingung verletzt: null";
        
        if (_euro > other._euro)
        {
            return true;
        }
        else if (_euro == other._euro)
        {
            if (_cent > other._cent)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Überpfüft, ob der andere Geldbetrag kleiner ist
     * 
     * @param other
     *            Der zu testende Betrag
     * @return true oder false
     * @require other != null
     */
    public boolean lesser(Geldbetrag other)
    {
        assert other != null : "Vorbedingung verletzt: null";
        
        if (_euro < other._euro)
        {
            return true;
        }
        else if (_euro == other._euro)
        {
            if (_cent < other._cent)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        String ausgabe = "";

        if (_euro < 10)
        {
            ausgabe = "0" + String.valueOf(_euro) + ",";
        }
        else
        {
            ausgabe = String.valueOf(_euro) + ",";
        }

        if (_cent < 10)
        {
            ausgabe += "0" + _cent;
        }
        else
        {
            ausgabe += _cent;
        }

        return ausgabe;
    }

    @Override
    public int hashCode()
    {
        return _euro + _cent;
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof Geldbetrag) && equal((Geldbetrag) o);
    }

    public boolean equal(Geldbetrag andererBetrag)
    {

        return _euro == andererBetrag._euro && _cent == andererBetrag._cent;
    }
}
