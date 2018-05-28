package little.nj.processor;

import java.util.Random;

public class Generator
{
    final static Random R = new Random ();

    final static String[] products = { "apples", "oranges", "pears", "bananas" };

    final static Messages.Adjustment.Operation[] ops = Messages.Adjustment.Operation.values ();

    static String product ()
    {
        return products[ R.nextInt ( products.length ) ];
    }

    static Messages.Adjustment.Operation operation ()
    {
        return ops[ R.nextInt ( ops.length ) ];
    }

    static int i ( int bound )
    {
        return R.nextInt ( bound );
    }

    static double d ( int scale )
    {
        return R.nextDouble () * scale;
    }

    public Messages.Sale sale ()
    {
        return new Messages.Sale ( product (), Math.ceil ( d ( 50 ) ) );
    }

    public Messages.Multi multi ( int count )
    {
        return new Messages.Multi ( product (), Math.ceil ( d ( 50 ) ), count );
    }

    public Messages.Adjustment adjust ( int scale )
    {
        return new Messages.Adjustment ( product (), operation (), Math.ceil ( d ( scale ) ) );
    }
}
