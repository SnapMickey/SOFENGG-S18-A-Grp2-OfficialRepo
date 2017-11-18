# SOFENGG-S18-A-Grp2-OfficialRepo


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
