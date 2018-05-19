# Scheduled Services

A simple project to help me create a jar file of tasks that I have to run using Windows Task Scheduler.

## Intended Use
Create an executable jar file and run from Windows Tasks Scheduler.

## Getting Started
Creating a new Process
- Create a new class implementing the Processor Iterface.
- Add logic in `process()` function.
- Add the process to a Service class' process list.

To create a new Output type implement the OutputNode interface.

Processes are bundled in Service classes. List of Services are executed when jar file is executed
```
		for(Service service : services) {
			service.start();
		}
```
