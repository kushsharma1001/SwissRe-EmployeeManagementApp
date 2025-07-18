# SwissRe-EmployeeManagementApp
This app is for Swiss Re Employee Management case study.

## Assumptions and Pre-requisites:

1. Managers with no reportees will not be evaluated for being overpaid or underpaid.
2. Only direct reportees are considered subordinates in the hierarchy and not the indirect ones.
3. For the sake of simplicity, app is written in Core Java SE without any frameworks or annotations involved.
4. The input csv file has to be put in src/main/resources/ folder with name: employees.csv
5. The direct subordinates of CEO will have no intermediate managers in between them and the CEO. Hence, Depth is assumed here as 0.

## How to run the app

1. git clone https://github.com/kushsharma1001/SwissRe-EmployeeManagementApp.git
2. cd app
3. Make sure you are inside the directory: SwissRe-EmployeeManagementApp/app
4. mvn clean package
5. java -jar target/app-0.0.1-SNAPSHOT.jar

## Design Principles Used

1. Various design patterns like Factory, Builder and Strategy have been used.
2. Logging has been implemented.
3. SOLID principles have been implemented like Single Responsibility Principle, Interface Segregation
