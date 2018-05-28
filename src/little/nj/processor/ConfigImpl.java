package little.nj.processor;

class ConfigImpl implements Config
{
    final static int DEFAULT_INTERVAL = 10;

    static void nat ( int x )
    {
        if ( x < 1 ) throw new IllegalArgumentException ();
    }

    final int limit;

    int interval;

    public ConfigImpl (int limit)
    {
        nat ( limit );

        this.limit = limit;
        this.interval = DEFAULT_INTERVAL;
    }

    public ConfigImpl interval ( int interval )
    {
        nat ( interval );

        this.interval = interval;
        return this;
    }

    public int interval () { return interval; }
    public int limit    () { return limit;    }
}
