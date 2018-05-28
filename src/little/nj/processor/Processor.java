package little.nj.processor;

public class Processor
{
    final Config config;
    final Store  store;
    final Logger logger;

    int count;

    public Processor ( Config config, Store store, Logger logger )
    {
        this.config = config;
        this.store  = store;
        this.logger = logger;
    }

    public void receive ( Message message )
    {
        if ( isPaused () )
        {
            throw new IllegalStateException ();
        }

        message.applyTo ( store );

        if ( ++count % config.interval () == 0 )
        {
            logger.report ( store );
        }

        if ( isPaused () )
        {
            logger.paused ();
            logger.report ( store.deltas () );
        }
    }

    public boolean isPaused ()
    {
        return config.limit () <= count;
    }
}
