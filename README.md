Notes

Approach

While completing the task, I first read the README file carefully several times.  
I identified the necessary changes in order to design a structure that also supports the bonus requirements.

To create a polymorphic structure for different transaction types, I built a model independent from specific transaction types.  
While doing this, I needed to research some modeling tricks in abstract classes.  
During this research, I learned that instead of passing a type field every time when creating an object, I could manage this automatically using the `@DiscriminatorColumn` annotation in abstract class hierarchies.

Implementation Details

I aimed to build a polymorphic structure that satisfies both the tests and the given request/response formats.
I tried to avoid breaking existing tests to stay aligned with TDD principles.  
  However, due to model structure changes, I had to modify or comment out some of them.
I didn't add any unnecessary features (no gold plating). I only implemented what was needed to meet the functional requirements.

What I Would Add If I Had More Time

1.Global Exception Handling: 
   To make error responses more readable and consistent, I would add a centralized exception handling mechanism.
2.Refactor Transaction Flow:
   I would move transaction operations completely to the service layer instead of managing them through the Account model.  
   This would be good for (pure anemic domain model) approach.
