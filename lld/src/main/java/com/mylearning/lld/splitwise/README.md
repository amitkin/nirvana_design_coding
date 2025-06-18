**Designing Splitwise**

Object-oriented design and implementation of Splitwise using Java.

**Prioritized Requirements**

    Users can add expenses.
    Users can edit expenses.
    Also, users can settle expenses.
    Allow users to make groups and add, edit and settle expenses in the group.

**Requirements not a part of our design**

    Comments for records.
    Activity log for every event.
    Authentication service.

**Objects definition**

User object

    class User{
        userID uid;
        string imageURI;
        string bio;
    }

Balance object

There are 2 types of people:

    People who will receive money from others. They have a positive balance.
    People who will pay money to others. They have a negative balance.

So our balance object should be able to handle both positive and negative values.

    class Balance {
        string currency;
        int amount; //For simplicity we are using integers here.
        // We can also use float or double but in that case, we also need to handle precision errors.
    }

Expense object

Expense objects must map each user to their balance.

    class Expense {
        ExpenseID eid;
        bool isSettled;
        map<User,Balance>;
        GroupID gid; //This expense belongs to which group
        
        //Metadata
        string title;
        int timestamp;
        string imageURI;
    }

Group object

    class Group{
        GroupID gid;
        List<User> users;
    
        //metadata
    
        string ImageURI;
        string title;
        string description;
    }

**Behavior definition**

Add Expense

    We have user and balance objects, and then we can persist them in the database.

Edit Expense
    
    Each expense object has a unique ID. We can use the ID to change the mapping or other metadata.

Settle Expense
    
    We make the isSettled flag true. We will use a balancing algorithm to settle expenses.

Add, Settle and Edit expenses in group
 
    Each expense object has a groupID. So we can add, settle and edit expenses in a group.


Balancing algorithm

***Problem***

Let us denote each user as a node and each payment as an edge in a graph.
So we need to minimize the number of edges in the graph


***Solution***

Note: When we talk about balance we are talking about the sum of all transactions for a user.


    First, let's divide the user into two categories

        People who have a positive balance.
        People who have a negative balance.

    If at the point any user has 0 balance that means his/her expenses are settled, and we can remove that node from the graph.


    At each step we will pick the largest absolute value from each category and add an edge between them.
    Let's say we pick A from the first category and B from the second category. So will add an edge from B to A (because B will pay and A will receive) and then update the balances. If the new balance of any node becomes 0 then we will not consider the node again.



Design Patterns Involved or Used:

    Factory Pattern:
        Use a factory pattern to create different types of expenses (EqualExpense, UnequalExpense, PercentageExpense).

    Observer Pattern:
        Implement an observer pattern to notify users/groups about changes in expenses or settlements.

    Strategy Pattern:
        Use a strategy pattern for splitting expenses (Equally, Unequally, by Percentage).

    Command Pattern:
        Implement a command pattern to encapsulate different expense operations.

    Facade Pattern:
        Utilize a facade pattern to simplify interactions and provide a unified interface to the system.



