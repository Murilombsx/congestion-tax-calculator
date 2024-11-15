***Focus***

- Find out the bug and create tests for the initial provided methods
covering all bug cases I identified.
- Create a better structure for the code, separating responsibilities and
introducing interfaces to decouple the code. Introducing some new packages, such
as api, model, repository and service.
- Focus on dependency injection, just providing to packages what they need(nothing more).
- Create struct for all vehicle types were missing, forcing them implement the
vehicle interface and adding a new attribute to say which one is toll free or not.
The idea behind is make the congestion calculator class change as less as possible
when new vehicles are introduced.
- Providing the new endpoint required and doing necessary validations and
transformations in the input to a format(model) that the whole application understands.
- In repository, created an in-memory implementation to retrieve tax rules that
has a flexible structure.

***Would like to have done***

- Introduce a library to allow to receive values from outside
and define environment variables (allow us deploy in different cities, consume
different databases, calculate taxes for different cities...)
- Do the database implementation in the repository package
- Structure the api layer better. Separate validations and converters from
the api layer.
- I assumed dates from the input were ordered, but I shouldn't.
- Organize the in-memory implementation better, code and data is very mixed up there.
- Lack of logging throughout the application.