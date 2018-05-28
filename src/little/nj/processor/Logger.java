package little.nj.processor;

public interface Logger
{
    void paused ();

    void report ( Store store );

    void report ( Store.Deltas deltas );
}
