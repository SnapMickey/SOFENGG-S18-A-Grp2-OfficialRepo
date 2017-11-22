# SOFENGG-S18-A-Grp2-OfficialRepo

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
- Connected front end pages together in SystemController
- Updated PcReservation Bean
- Updated Login Filters, made code cleaner.

Errors/Problems:
- Occasional error where login redirects but does not enter into servlet. Unsure whether the error will reappear. 

Lominoque, Jerome November 16, 2017
Changes made:
- Finalized Admin Reservation Page
- Added new column in pc_reservations table -> eventName

Lominoque, Jerome November 14, 2017
Changes made:
- Can now access reservation page
- Updated SQL based on ERD
- pc_reservations table has data

Errors/Problems:
- Reservation page is not yet finalized and is not yet working properly will fix immediately

Reminders:
- Logs for Database is not yet applied (When Inserting and Deleting or backup logs)
- Encryption for Database is not yet applied

Bornales, Amiel James November 14, 2017
Changes made:
- fixed error messages in servlet
- added user_reserve.html

Bornales, Amiel James November 13, 2017
Changes made:
- updated headers
- updated layouts for admin and user home pages
- added scroll for table data
- removed backgrounds in admin and user home pages
- updated table format

Errors / Problems:
- No Icons yet for admin home, still waiting for BA reply
- index.html not yet updated, still waiting for BA reply

Lominoque, Jerome November 2, 2017
Changes made:
- Added an updated version of sql

Rivera, Jared November 2, 2017
Changes made:
- System Service, finished database getters: Pc, Lab, PcReservation.


Bornales, Amiel James November 2, 2017
Changes made:
- Finished layout of admin_home_page
- Finished layout of user_home_page
- added functions for adding data to the tables in admin home page
- added functions for adding data to the tables in user home page
- fixed footer

Errors / Problems:
- Header alignment
- Background image not staying when scrolling down
- Admin's reserve button doesnt redirect yet

Lominoque, Jerome November 2, 2017
Changes made:
- Edited the sql.
    - Changed all boolean (VARCHAR) to boolean (TINYINT)
    - removed year in faculty table
    - changed year to status in student table
- Added Admin Reservation Page (Filename: admin_reservation_page)
 
Error / Problems:
- Header spacings must be fixed
    
Rivera, Jared November 2, 2017
Changes made:
- Added Pc, Lab, Pc Reservation Beans

Rivera, Jared November 1, 2017
Changes made:
- Filters work. Page security is accounted for
- Fixed displaying of alert for restricted access in index.html

Error / Problems:
- Background does not display


Bornales, Amiel October 31, 2017
Changes made:
- Background works now
- Fixed displaying of proper error messages
- Added Restricted Access alert

Error / Problems:
- Filter needs to redirect to "index.html?error=RA" for alert to work 

Rivera, Jared October 31, 2017
Changes made:
- Modified Filters in filter package

Error/Problems:
- Still requires code to display an error alert. Line  58 & 62.


Lominoque, Jerome October 30, 2017
Changes made:
- Applied Filters in filter package
- Edited SystemController.java
- Added the following methods:
    - UserPage
    - AdminPage
    
Error/Problems:
- After logging out, the user may press back and see the contents. But if the user refreshed the browser then he will be redirected to the login. 
- The user may press enter on the address bar and may return back to the userpage/adminpage after being redirected.
