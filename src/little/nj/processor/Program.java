package little.nj.processor;

public class Program
{
    public static void main ( String[] args )
    {
        Generator generator = new Generator ();
        Config    config    = new ConfigImpl ( 50 ).interval ( 10 );
        Processor processor = new Processor ( config, new StoreImpl (), new LoggerImpl () );

        do
        {
            Message msg = null;

            double p = Generator.d ( 1 );

            if      ( 0.50 < p ) msg = generator.sale ();
            else if ( 0.75 < p ) msg = generator.multi ( 10 );
            else                 msg = generator.adjust ( 2 );

            processor.receive ( msg );

        } while ( !processor.isPaused () );
    }
}
