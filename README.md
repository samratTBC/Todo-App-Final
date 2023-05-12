# Todo-App-Final

## Project Brief:

This is java based android todo app which lets user to create quick todo. This App is built by implementing MVVM architecture.
Model — View — ViewModel (MVVM) is the industry-recognized software architecture pattern that overcomes all drawbacks of MVP and MVC design patterns. MVVM suggests separating the data presentation logic(Views or UI) from the core business logic part of the application.


## Todo APP Feature
* Add task (Insert)
* Delete Task
* Update Task


# Architecture Followed: MVVM




## MVVM PICTORIAL DESCRIPTION:

| MVVM Architecture  | app implemented |
| ------------- | ------------- |
| ![pattern](https://github.com/samratTBC/Todo-App-Final/assets/110808053/2717f06a-4cbd-49ac-8531-9e423d566b33) |  ![image](https://github.com/samratTBC/Todo-App-Final/assets/110808053/4c28c836-d014-4655-8653-9630f6ad2147)|



## MVVM Brief:
* Entity:
An Entity is a plain old Java object (POJO) that represents a table in the database. It contains fields for each column in the table and is annotated with the @Entity annotation to indicate that it is an entity.

* DAO (Data Access Object):
A DAO is an interface that defines the methods for accessing the data from a database. It provides a way for the rest of the application to interact with the database without exposing the implementation details. In Android, a DAO is typically defined using the Room library.

* RoomDatabase:
Room is a persistence library that provides an abstraction layer over SQLite. It allows you to create a database with pre-defined tables and relationships using annotations. The RoomDatabase is a class that represents the database and serves as the main access point for the rest of the application.

* Repository:
A Repository is a class that acts as a mediator between the ViewModel and the DAO. It provides a clean and consistent API for the rest of the application to interact with the database. The Repository is responsible for fetching data from the database using the DAO, performing any necessary business logic, and returning the data to the ViewModel. It also handles any caching or synchronization of the data.

* ViewModel:
A ViewModel is a class that represents the UI data and logic for a particular view. It communicates with the Repository to fetch the data and provides observable data to the View. It also handles user interactions and updates the data in the Repository as needed.

* LiveData:
LiveData is an observable data holder class that is part of the Android Architecture Components. It allows the ViewModel to provide data to the View in a way that is lifecycle-aware. This means that the data will only be updated when the View is in the active state, and it will automatically be cleaned up when the View is destroyed.

* Observer:
An Observer is an interface that is used to receive updates from LiveData. It is implemented by the View and is notified whenever there is a change in the data. The Observer then updates the UI to reflect the new data.

* Together, these components work together to provide a clean and consistent architecture for Android app development. The Entity represents the data in the database, the DAO provides the methods for accessing the data, the RoomDatabase provides the actual database implementation, the Repository provides a clean API for accessing the data, the ViewModel provides the UI logic and data, and the LiveData and Observer provide the mechanism for updating the UI in a lifecycle-aware way.







# APP Working

| Insert/Create  | Select/Unselect/Delete | Edit |
| ------------- | ------------- | ------------- |
|![create](https://github.com/samratTBC/Todo-App-Final/assets/110808053/08d89ef6-c88b-497c-bb47-34df0cee3986)|![opt](https://github.com/samratTBC/Todo-App-Final/assets/110808053/7a56c8bb-5f49-410b-871e-00bdc3f8c309)|![edit](https://github.com/samratTBC/Todo-App-Final/assets/110808053/b31d95ad-cbd8-437b-955a-76befbe1689a)|


| Search Feature  | Menu Options(Delete, Intent to web, Layout change | Sort By completion |
| --------------- | ------------------------------------------------- |------------------- |
|![searchFeature](https://github.com/samratTBC/Todo-App-Final/assets/110808053/1cd142f1-4de7-4e1f-badf-357a47407d9e)|![delete and menu options](https://github.com/samratTBC/Todo-App-Final/assets/110808053/cb53ed90-1d74-4492-a196-596420d680a1)|![sort](https://github.com/samratTBC/Todo-App-Final/assets/110808053/94317713-7a5d-4770-85ca-d16b65ac0aba)|



