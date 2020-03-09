
**Software Testing Project Proposal**

*Spring 2020*

For our group project, we propose creating a software package which will generate input and validate appropriate results for an API under test. This will allow us to test the efficacy of tests with a broad variety of inputs.

Using a simple Spring Boot Based Web API as our test subject, we will automate a process which can create a variety of different HTTP requests with both successful and failing responses. Each generated test will include input data as well as data defining the expected response. We will generate various requests with distinct bodies, headers, methods, etc. Both the input and expected responses will be generated from metadata in a form such as OpenAPI that defines the API's contracts. For each generated test we would then verify the correct responses from the API, and report those back to the developer.

This project will be written in Java.
