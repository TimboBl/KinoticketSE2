package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;



/**
 * Ein Fachwert zur Repr‰sentation von Geldbetr‰gen f¸r das Kinosystem
 * @author Balthasar Spanner
 *
 */
public final class Geldbetrag
{
    private final int _euro;
    private final int _cent;

    /**
     * Initialisiert ein Geldbetragsobjekt
     * @param euro Der Eurowert
     * @param cent Der Centwert
     * @require cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer"
     */
    private Geldbetrag(int euro, int cent)
    {
        assert cent <= 99 : "Vorbedingung verletzt: Zu viel Cents du Opfer";

        _euro = euro;
        _cent = cent;
        
    }

    /**
     * ÷ffentliche Schnittstelle f¸r den Fachwert
     * @param euro Der Eurowert
     * @param cent Der Centwert
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
     * Addiert zwei Geldbetr‰ge
     * @param other Der zu addierende Geldbetrag
     * @return Ein neues Geldbetragsobjekt
     */
    public Geldbetrag plus(Geldbetrag other)
    {
        assert other != null : "Vorbedingung verletzt: Der zu addierende Betrag darf nicht null sein";

        int tempCents = _cent + other.getCent();
        int tempEuro = tempCents / 100;
        tempCents %= 100;
        tempEuro += _euro + other.getEuro();

        return Geldbetrag.get(tempEuro, tempCents);
    }

    /**
     * Subtrahiert zwei Geldbetr‰ge
     * @param other Der zu Subtrahierende Geldbetrag
     * @return Ein neues Geldbetragsobjekt
     */
    public Geldbetrag minus(Geldbetrag other)
    {
        String eurocentBetrag = String.valueOf(_euro) + String.valueOf(_cent);
        String eurocentsInput = String.valueOf(other.getEuro()) + String.valueOf(other.getCent());
        
        int tempBetrag = Integer.valueOf(eurocentBetrag); 
        int tempInput = Integer.valueOf(eurocentsInput);
        
        int tempValue = Math.abs(tempBetrag - tempInput);
        
        int tempCent = tempValue % 100;
        int tempEuro = (tempValue - (tempValue % 100)) / 100;
        
        return Geldbetrag.get(tempEuro, tempCent);
        
        
        
    }

    /**
     * Multipliziert einen Geldbetrag mit einer Zahl
     * @param other Der Multiplikator
     * @return Ein neues Geldbetragsobjekt
     */
    public Geldbetrag mal(int multiplikator)
    {
        int tempCent = Math.abs(_cent * multiplikator);
        int tempEuro = tempCent / 100;
        tempCent %= 100;
        tempEuro += Math.abs(_euro * multiplikator);
        
        return Geldbetrag.get(tempEuro, tempCent);
    }

    /**
     * Konvertiert einen String zu einem Geldbetrag
     * @param betrag Der zu konvertierende String
     * @return Ein neues Geldbetragsobjekt
     */
    public static Geldbetrag stringToGeldbetrag(String betrag)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Konvertiert einen Integer zu einem Geldbetrag
     * @param betrag Der zu konvertierende Integer
     * @return Ein neues Geldbetragsobjekt
     */
    public static Geldbetrag integerToGeldbetrag(int betrag)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * ‹berpr¸ft, ob der andere Geldbetrag grˆﬂer ist
     * @param other Der zu testende Betrag
     * @return true oder false
     */
    public boolean greater(Geldbetrag other)
    {
        if (_euro > other.getEuro())
        {
            return true;
        }
        else if (_euro == other.getEuro())
        {
            if (_cent > other.getCent())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * ‹berpr¸ft, ob der andere Geldbetrag kleiner ist
     * @param other Der zu testende Betrag
     * @return true oder false
     */
    public boolean lesser(Geldbetrag other)
    {
        if (_euro < other.getEuro())
        {
            return true;
        }
        else if (_euro == other.getEuro())
        {
            if (_cent < other.getCent())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt den Euro Betrag als int zur¸ck
     * @return den Euro Betrag
     */
    private int getEuro()
    {
        return _euro;

    }

    /**
     * Gibt den Cent Betrag als int zur¸ck
     * @return den Cent Betrag
     */
    private int getCent()
    {
        return _cent;

    }

    @Override
    public String toString()
    {
        String ausgabe = "";

        ausgabe = "0" + String.valueOf(_euro) + ",";

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

        return _euro == andererBetrag.getEuro()
                && _cent == andererBetrag.getCent();
    }
}
