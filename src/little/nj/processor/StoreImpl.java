package little.nj.processor;

import java.util.HashMap;

class StoreImpl extends HashMap< String, Store.Sales > implements Store
{
    static class DeltasImpl extends HashMap< String, Double > implements Deltas { }

    static class SalesImpl extends HashMap< Double, Integer > implements Store.Sales
    {
        public SalesImpl () {}

        public SalesImpl ( double v, int x )
        {
            put ( v, x );
        }
    }

    final Deltas deltas = new DeltasImpl ();

    public void put ( Messages.Sale sale )
    {
        merge ( sale.type, new SalesImpl ( sale.value, 1 ), Store::merge );
    }

    public void put ( Messages.Multi sale )
    {
        merge ( sale.type, new SalesImpl ( sale.value, sale.x ), Store::merge );
    }

    public void put ( Messages.Adjustment adjust )
    {
        computeIfPresent ( adjust.type, ( x, y ) -> {
            SalesImpl z = new SalesImpl ();

            for ( Double k : y.keySet () )
            {
                double v = adjust.apply ( k );
                double d = v - k;

                z.put ( v, y.get ( k ) );

                deltas.merge ( adjust.type, d, Double::sum );
            }

            return z;
        } );
    }

    public Deltas deltas ()
    {
        return deltas;
    }
}
