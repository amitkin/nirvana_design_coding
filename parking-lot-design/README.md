# Design Patterns

## Questions to be asked
1. How is the Parking Structure designed, is it open space or in building?
2. Accessibility - Are some parking spaces only accessible if other parking spaces are free?
3. How many parking spaces are we talking about 10 or 1000?
4. Does this have multiple parking levels? And is there dependency like filling upper levels first.
5. Is it having multiple entrances?
6. Pricing Model - If multiple sizes of parking spaces available then pricing strategy.

## Singleton Pattern
- There can be only one parking structure and every vehicle should not create its own parking structure.

## Factory Pattern
- Suppose there is License enum and based on the different License like Public/Private Parking structure return the instance.

Both Singleton and Factory pattern has private constructor

## Strategy Pattern
- To apply different pricing on different days like WEEKDAY, WEEKEND in ParkingStructure's calculatePayment.
- For this we might use enum and based on different enum values of PaymentAlgorithm we can have different pricing.
- Or we go for strategy pattern and avoid multiple if else
- Also pricing can be based on different types of parking spot based on his usage

## Concurrency
- If we have multiple entrances then race condition can happen while accessing same parking space at same time.



