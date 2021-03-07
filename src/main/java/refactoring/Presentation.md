# Sheesh, this code smells...
## Code smells, how to recognize them, and how to refactor them

## What are code smells?
> "A code smell is a surface indication that 
> usually corresponds to a deeper problem in the system." -- Martin Fowler

- Usually quick to spot _sniffable_
- Determining what is and is not a code smell can be subjective  
- They don't _always_ indicate a problem, but you should investigate
- It comes with experience

## Why should we care about code smells?
- Pride in craftsmanship
- Makes the software harder to understand
- Bugs live in filth
- Makes it harder to add new features

## Popular Code Smells
- Duplicated Code (CareTakerImpl)
- Long Method (PetOwner)
- Long Parameter List (PetGroomer)
- Comments
- Switch Statements
- Large Class
- Shotgun Surgery

## How do you remove the smells?
- By cleaning up code using refactoring
- Refactoring: Change internal with affecting external behavior 
- There are a number of techniques
    - Extract Method
    - Inline Method
    - Introduce Explaining Variable
    - Move Method
    - Extract Class/Interface
    - Remove Conditional Flag
    - Replace Conditional with Polymorphism
  
## How do we have confidence in the refactoring?
- When we change the code, we introduce risk
- We mitigate risk by testing
- Make sure you have test coverage before you refactor
- Refactor, then re-run your tests
    
## References
- [Refactoring, Improving the Design of Existing Code](https://martinfowler.com/books/refactoring.html)
- [Clean Code, A Handbook of Agile Software Craftsmanship](https://martinfowler.com/books/refactoring.html)