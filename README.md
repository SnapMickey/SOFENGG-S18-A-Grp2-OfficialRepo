# SOFENGG-S18-A-Grp2-OfficialRepo

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
