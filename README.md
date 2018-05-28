## Message Processor

A simple message processor for financial transactions on various
products.

### Style Guideline

For readers, overall, "be brief" is the rule:

- omit default access modifiers;
- block format members;
- keep file number small;
- extend or use base types.

### Design

Data driven:

- `Processor`: controls flow;
- `Config`: configures the `Processor`;
- `Store`: manages state;
- `Logger`: handles logging.

DI is unused but designed for, e.g. especially the Config interface.

#### The `Message` Interface

Its `applyTo` method at first glance, doesn't do much, it's preventing
the processor from knowing too much about the domain while performing
double dispatch and preventing a type check.

I wasn't sure about including this method as it introduces some
duplication and could raise an eyebrow or two... hence this
explanation.

### Generation

It seemed realistic to favour the `Sale` message type:

- ~= 50% being Sales;
- ~= 25% being Multi-Sales;
- ~= 25% being Adjustments.

### TODO

- error checking;
- extract the specific `Store` interface;
- Ant build configuration;
- extend the `Generator` so it's more useful for;
- UNIT TESTS.

In this particular case the missing unit tests aren't _that_ critical
since business logic is implemented through map merging and library
provided mathematical functions - from the specification the first
tests I'd write would be for the `Logger` method calls.
