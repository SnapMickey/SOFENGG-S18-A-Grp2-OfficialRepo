# SOFENGG-S18-A-Grp2-OfficialRepo
Bornales, Amiel James December 2, 2017
Changes made:
- added header button functionality to admin_history.html
- added functionality for when user scrolls to bottom of page in admin_history.html
- updated admin_history.html to latest mockup
- added listeners for when start/end date is changed
- added clear table function

Errors/Problems:
- function for checking if user scrolls to bottom of table doesnt pull data from db yet
- table does not recieve table data yet
- cannot filter history based on start / end dates yet

Bornales, Amiel James November 30, 2017
Changes made:
- updated index.html to latest mockups (it was already updated on my laptop, I just forgot to push sorry)

Lominoque, Jerome R. November 23, 2017
Changes made:
- Fixed Admin reservation page
    - Buttons are matching the mockups
    Notes:
    - Button size is not yet fixed
    
- Fixed SystemController Time for schedule list
- Prepared User reservation page
    - changed reserveButton -> disabledReserveButton
    
- Fixed User reservation page

Errors/Problems:
- Time is not reflecting properly in the database (5:00PM -> (in db = 5:00AM))

Bornales, Amiel James November 23, 2017
Changes made:
- updated index.html error messages to current mockup
- added modals and functions to user and admin home


Bornales, Amiel James November 22, 2017
Changes made:
- added functionality for Layout buttons in user / admin reserve
- fixed height for user_front_page.html
- fixed height for admin_front_page.html

Errors / Problems:
- When some views are zoomed in very close (500% or something) some content cannot be seen

Lominoque, Jerome November 22, 2017
Changes made:
- Finalized admin front

Notes:
- Encryption is not yet applied
- Database log(s) is/are not yet applied

Lominoque, Jerome November 22, 2017
Changes made:
- Fixed admin front page problems
- Fixed some System Service bugs
- fixed some systemcontroller bugs

<<<<<<< HEAD
Rivera, Jared November 22, 2017
Changes made:
- Cleaned and Finalized ReservationBuilder
=======
Lominoque, Jerome November 22, 2017
Changes made:
- Fixed user and admin reservation page
- Fixed admin front page
- fixed some systemservices event problems

Errors/Problems:
- Incorrect data output for schedule list in admin front page
- data not showing for lab reservation table in admin front page

Notes:
- User reservation page is not yet final but I left some comments 

Bornales, Amiel James November 21, 2017
Changes Made:
- fixed error in login.html where user is getting DNE / Wrong Password when ID is valid but password is blank
>>>>>>> 0a3ac088c1f0f942c1d7ad21d515522ae9134ce4

Lominoque, Jerome November 21, 2017
Changes made:
- Finalized admin reservation page

Notes:
- Started working on user reservation page

Rivera, Jared November 21, 2017
Changes made:
- Added LabReservation bean
- Updated attributes inside PcReservation
- added methods for connections to lab_reservation table methods in System Service
- updated methods in SystemService: getAllFreePcs(), getAllFreeLabs(). Replaced old logic
- Renamed several names to clarify method functionalities
- Renamed PcReservationBuilder to ReservationBuilder
- Added static method in PcReservationBuilder: generateLabReservations()

Errors/Problems
- affected methods of the renaming process, used in SystemController are not yet refactored. 

Lominoque, Jerome November 20, 2017
Changes made:
- Added lab_reservations table
- added auto increment attributes to some tables
- added default value for adminconfirmed 
- fixed some bugs in admin reservation page

Rivera, Jared November 20, 2017
- Fixed SystemService methods: getAllFreePcs()
- Added close methods for the EntityManagerFactory in SystemService to prevent full db connection pool

Lominoque, Jerome November 19, 2017
Errors fixed:
- Error handling is now working
- Pop-up box is now working for admin reserve

Errors Remaining:
- Reservation for class/event is not working
- Setting "Any" in dropdown "Time" will cause the db connection to crash
- Setting the dropdown "Time" to any value except "Any" will cause the db connection to crash after 3 attempts of searching

Lominoque, Jerome November 19, 2017
Major Update:
- Reservation for Admin (Individual) is now Working!
Changes made:
- Admin Reservation Page
- SystemController
    - added finalized reserveSinglePc and requestAdminReservationList
    
Errors/Problems:
- Error handling stuff are not yet applied (front-end)
- No pop-up box yet (on reserve)
- Reservation for class/event is not working
- Setting "Any" in dropdown "Time" will cause the db connection to crash
- Setting the dropdown "Time" to any value except "Any" will cause the db connection to crash after 3 attempts of searching


Reminders/Notes:
- second statement after (room != null) must be commented out so that requestAdminReservationList would work

    
Rivera, Jared November 19, 2017
Changes made:
- fixed recently added SystemService methods
- implemented servlet methods for home pages and reserve
- added new constructor for PcReservation bean
Errors/Problems
- reserve is not fully implemented. Still requires parameters from request and transformation into addReservation() parameters

Bornales, Amiel James November 19, 2017
Changes made:
- user_reserve now has complete functions for adding and selecting data in table
- user_reserve header now works and is connected to servlets
- user_reserve filters now has basic functionality on filtering rooms

Errors/Problems:
- applying the filter wont pull from db yet
- cannot pull data from db yet when displaying available PC's

Rivera, Jared November 18, 2017
Changes made:
- Updated SystemService methods: getAllFreePcs()
- Created SystemService methods for Lab reservations: getAllFreeLabPcs(), getAllLabReservations()
- Renamed SystemService method getAllReservations() to getAllSingleReservations. Added location parameter.
- Created SystemService methods: addReservation(), removeReservation()
Errors/Problems:
- with current database settings, cannot return freepcs because pcs are all either not available or the labs they occupy are not available

Lominoque, Jerome November 18, 2017
Changes made:
- Updated admin_reservation_page
- Updated Database (added more rows/data)
- Edited admin_front_page
    - can now show data (recent reservations only)
- Updates on SystemController:
    - Added "/requestAdminReservationList" in servlet "SystemController"
    - Added "/getAdminSchedules", "/getLabReserations", "/getRecentReservations"
    - Added functions:
        - requestScheduleList
        - requestLabReservations
        - requestRecentReservations
    - Added function in "SystemController" named "requestAdminReservationList"

Errors/Problems:
- Reservation Process for Admin
    - Functionality of reserve and getting all possible reservations are not yet working
- Admin Front Page
    - Lab Reservations and Schedules are not yet showing the appriopriate data
- Error handling:
    - No ID num input
    - Incorrect ID num input
- UI Problems:
    - No pop-up box to show that the user reserved
    
Bornales, Amiel James November 18, 2017
Changes made:
- Added admin_history.html

Errors / Problems:
- fixed height of several html, but Jared's functionality wasnt implemented in the html yet. Will fix soon

Lominoque, Jerome November 17, 2017
Changes made:
- Updated Admin Reservation Page
    - Made all of the dropboxes dynamic (Values depend on the current date or the other values of other dropboxes)


Rivera, Jared November 17, 2017
Changes made:
- Implemented functionality of user home page
- Added SystemService method: findLabOfPc()
- Added two url patterns and methods: requestUserDetails(), requestUserReservations()

Rivera, Jared November 17, 2017
Changes made:
- Fixed logout button listener
- Added listeners for buttons: Home, Dashboard, Reserve.
- Connected front 
