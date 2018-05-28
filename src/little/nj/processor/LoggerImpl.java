package little.nj.processor;

import java.util.Map;

class LoggerImpl implements Logger
{
    public void paused ()
    {
        System.out.println ( "Paused" );
    }

    public void report ( Store store )
    {
        System.out.println ( "Sales:" );

        for ( Map.Entry< String, Store.Sales > x : store.entrySet () )
        {
            int    num = 0;
            double sum = 0;

            for ( Map.Entry< Double, Integer > y : x.getValue ().entrySet () )
            {
                num += y.getValue ();
                sum += y.getKey () * y.getValue ();
            }

            System.out.printf ( "%8s: %2d for %6.2f%n", x.getKey (), num, sum );
        }
    }

    public void report ( Store.Deltas deltas )
    {
        System.out.println ( "Adjustments:" );

        for ( Map.Entry< String, Double > x : deltas.entrySet () )
        {
            System.out.printf ( "%8s: %6.2f%n", x.getKey (), x.getValue () );
        }
    }
}
