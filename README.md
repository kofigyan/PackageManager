Package Manager App
===========================================================

This app allows users to track apps installed on phone and manage them(changed,installed,uninstalled).


Introduction
-------------

### Functionality
The app is composed of one main screen.
#### MainActivity
Allows the user to view all installed apps.
The user can also install,uninstall and launch apps. More 
through broadcast listeners the user is alerted to changes  
to packages (ie. added,changed and removed).
 
  
### Building
You can open the project in Android studio and press run.
### Testing
Still in development. 
The project will uses both instrumentation tests that run on the device
and local unit tests that run on your computer. 

#### Device Tests
##### UI Tests
The projects will use Espresso for UI testing.  

#### Local Unit Tests 


### Libraries
* [Android Extension Libraries][extension-lib]
* [Android Architecture Components][arch]  
* [espresso][espresso] for UI tests
* [mockito][mockito] for mocking in tests
 


[extension-lib]: https://developer.android.com/jetpack/androidx
[arch]: https://developer.android.com/topic/libraries/architecture
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/ 
[mockito]: http://site.mockito.org 

 
