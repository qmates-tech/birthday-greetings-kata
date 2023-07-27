# The Birthday Greetings Kata

Implement a birthday greetings software that uses emails to greet his users on birthday.

Purpose: to learn about the hexagonal architecture, which is a good way to structure an application,
and how to shield your domain model from external apis and systems.

## Problem

Write a program that:
- loads a set of employee records from a flat file
- sends a greetings email to all employees whose birthday is today

The flat file is a sequence of records, separated by newlines; this are the first few lines:

```
last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com`
```

The greetings email contains the following text:

```
Subject: Happy birthday!

Happy birthday, dear John!
```

with the first name of the employee substituted for "John".

The program should be invoked by a main program like this one:

```
public static void main(String[] args) {
    ...
    BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);
    birthdayService.sendGreetings(today());
}
```
Note that the _collaborators_ of the birthdayService objects are _injected_ in it.<br/>
Ideally domain code should _never_ use the `new` operator. 
The `new` operator is called from outside the domain code, 
to set up an aggregate of objects that collaborate together.

## Goals

The goal of this exercise is to come up with a solution that is

* Testable: we should be able to test the internal application logic with no need to ever send a real email.
* Flexible: we anticipate that the data source in the future could change from a flat file to a relational database, or perhaps a web service. We also anticipate that the email service could soon be replaced with a service that sends greetings through Facebook or some other social network.
* Well-designed: separate clearly the business logic from the infrastructure.


## Going further

### An optional complication

If you want to develop further the domain logic, you can take into account the special rule for people born on a 29th of February: 
they should be sent greetings on the 28th of February, except in leap years, when they will get their greetings on the 29th.

### Test categories

A test is not a unit test if:

- It talks to a database
- It communicates across the network
- It touches the file system
- You have to do things to your environment to run it (eg, change config files, comment line)

Tests that do this are _integration tests_.
Integration tests have their place, but they should be clearly marked as such, so that we can execute them separately.

The reason we draw this sharp distinction is that unit tests should be:

- Very fast: we expect to run thousands of tests per second.
- Reliable: we don’t want to see tests failing because of random technical problems in external systems.

### Testability

One way to make code more testable is to use _Dependency Injection_. This means that an object should **_never_** instantiate its collaborator by calling the new operator. It should be passed its collaborators instead.

When we work this way we separate classes in two kinds:
- _Application logic_ classes have their collaborators passed into them in the constructor.
- _Configuration classes_ build a network of objects, setting up their collaborators.

Application logic classes contain a bunch of logic, but no calls to the new operator. Configuration classes contain a bunch of calls to the new operator, but no application logic.

------

> Thanks to our friend [Matteo Vaccari](https://github.com/xpmatteo) who originally devised and described this kata.<br/> 
> This is the original blog post with more valuable advice: http://matteo.vaccari.name/blog/archives/154
