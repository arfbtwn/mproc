package little.nj.processor;

import java.util.Map;

public interface Store extends Map< String, Store.Sales >
{
    interface Sales extends Map< Double, Integer >
    {
        default void merge ( Store.Sales x )
        {
            for ( Entry< Double, Integer > e : x.entrySet () )
            {
                merge ( e.getKey (), e.getValue (), Math::addExact );
            }
        }
    }

    interface Deltas extends Map< String, Double > { }

    static Store.Sales merge ( Store.Sales x, Store.Sales y )
    {
        y.merge ( x );
        return y;
    }

    void put ( Messages.Sale sale );

    void put ( Messages.Multi multi );

    void put ( Messages.Adjustment adjustment );

    Deltas deltas ();
}
