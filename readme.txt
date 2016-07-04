DEPLOYMENT:

- Project was developed in the official IDE for Android recommended and supported by Google. You can download it here https://developer.android.com/studio/index.html
- Open the project with File -> Open.
- If lint error blocking build process - disable lint check. You could do it by running gradle from the command line with the "./gradlew check -x lint"

FUNCTIONALITY:

- App flow based on this design https://drive.google.com/folderview?id=0B9qFIpKOvgbmN0NJcEowcUtMWHc&usp=drive_web

- User gets to Assessment Intro flow by tapping on “Get Started” from landing page 00-0_Landing Page

- To login you first need to create an account on the page 00-8_Landing Page_Create Account

- Using email and password, that you wrote on previous step you can later login on the page 01-0_Login

- Module progress on screen 03-1C_Module BC_Step Progress simulates the rise, as you step through the pages.

- Dashboard progress for the Business Continuity Plan 1st and 2nd level simulates the rise, as you go through the modules. 02-0_Dashboard. Other modules also could be changed dynamically, when will be implemented.

- Tapping on the widget on Dashboard header will take user to the corresponding module flow (BC flow implemented).

- Click on the home button 03-1A_Module BC_Step 1 moves you to the dashboard.

- To go to the Profile screen - tap on user avatar in the dashboard screen

- Name and email on the profile screen 02-3_Dashboard_Profile are got from the data you have filled on the sign-up page.

- Organisation name on the page 08-1_Assessment lv1_Certificate is filled from the data you have filled on the sign-up page.

- User gets to the final Assessment flow when user finishes the BC module.