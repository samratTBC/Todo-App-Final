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
| ![image](https://github.com/samratTBC/Todo-App-Final/assets/110808053/eac91f40-b0cf-40e2-b285-8b73e22bf620) |  ![pattern](https://github.com/samratTBC/Todo-App-Final/assets/110808053/1c4f18a7-9079-40a5-ae41-9eedd80834c3)|



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
| ![create](https://github.com/samratTBC/Todo-App-Final/assets/110808053/449f3dd3-7218-4426-ada2-c4a0db91ed9c) |  ![opt](https://github.com/samratTBC/Todo-App-Final/assets/110808053/4203926e-f523-4c70-8423-e76034841444)             | ![edit](https://github.com/samratTBC/Todo-App-Final/assets/110808053/8535a875-95c4-46f6-b284-3a5091c12e12)  |


| Search Feature  | Menu Options(Delete, Intent to web, Layout change | Sort By completion |
| --------------- | ------------------------------------------------- |------------------- |
| ![searchFeature](https://github.com/samratTBC/Todo-App-Final/assets/110808053/84444ed6-a478-438b-8832-90f4f1f73d46) | ![delete and menu options](https://github.com/samratTBC/Todo-App-Final/assets/110808053/c54370b2-a5e1-47cd-a89f-0ab03219c9d7)  | ![sort](https://github.com/samratTBC/Todo-App-Final/assets/110808053/93582372-5fca-4eea-8a83-96b060a7665b) |



