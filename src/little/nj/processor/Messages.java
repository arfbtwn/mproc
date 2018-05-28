package little.nj.processor;

public class Messages
{
    private Messages () {}

    public static class Sale implements Message
    {
        public Sale ( String type, double value )
        {
            this.type  = type;
            this.value = value;
        }

        public final String type;
        public final double value;

        public void applyTo ( Store store )
        {
            store.put ( this );
        }
    }

    public static class Multi extends Sale
    {
        public Multi ( String type, double value, int x )
        {
            super ( type, value );

            this.x = x;
        }

        public final int x;

        public void applyTo ( Store store )
        {
            store.put ( this );
        }
    }

    public static class Adjustment implements Message
    {
        public enum Operation
        {
            ADD, SUBTRACT, MULTIPLY
        }

        public Adjustment ( String type, Operation op, double x )
        {
            this.type = type;
            this.op   = op;
            this.x    = x;
        }

        public final String    type;
        public final Operation op;
        public final double    x;

        public double apply ( double current )
        {
            switch ( op )
            {
                case ADD:      return current + x;
                case SUBTRACT: return current - x;
                case MULTIPLY: return current * x;

                default:
                    throw new IllegalArgumentException ();
            }
        }

        public void applyTo ( Store store )
        {
            store.put ( this );
        }
    }
}
