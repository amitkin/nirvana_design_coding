Air traffic control system is a classic example of mediator design pattern. 
1) Define an interface for mediator/controller. 
2) Define another interface for client/aircraft. 
3) Define concrete impl for the mediator interface.
4) Define concrete impl for the flight interface. 

Each flight object needs to register with the mediator.

Three fundamental objects i see are:
 1) Aircraft
 2) ATS Controller
 3) Common Resources
 
Aircraft basically seeks permissions for various stages and Controller fundamentally validates and grants. In the process of validation, controller communicates with other 'Aircraft' objects and interacts with 'common resources'

Various operations/member functions across these classes could be as follows :

Aircraft :
- pre-flight activities
- take-off
- inFlight
- descent
- landing

Note - Every stage is time-bound, sending/receiving grants based on start/end times.

Controller/base
- Weather report engine (common for location)
- grant pre-flight
- grant take-off
- monitor inFlight route
- monitor arrivals, departures, transits
- Two-way Communication with Controllers at other locations
- Provide Alerts (categorized based on severity)
- Address emergenies ( Ex- Engage rescue flights when needed etc)
- Constantly poll all aircrafts in jurisdiction.

Note - Controller class can internally use 'descision trees' to 'Grant' a given permission based on interaction with other Aircraft objects and resources.

Implementation of Controller
1. It will maintain list of Aircrafts
2. It will have operations as mentioned above for example grant pre-flight will call internally pre-flight method on aircraft


Common Resources:
- Runways availability/condition
- Engineering staff
- Flight safety crew

ATS system as such is mission-critical and should be extremely fault-tolerant and with Zero down-time. To meet these goals, reduncancies could be present for 'Controller' objects (say primary, secondary controllers) which ensure to give grants/alerts.
Also Controllers should continuously 'Poll' the black-boxes (think of them as connected IPs) for constant 'in-touch' situation.






















