# ThermalPrinter
Cordova Thermal Printer App for ZPOS-Z91 Device 


# This is Cordova Plugin which will only work on ZPOS-Z91 Device as it is using its proprietary drivers


# Methods 
The Plugin only provides 2 methods. 
   
   ThermalPrinterPlugin.init(); 
   
and 

   ThermalPrinterPlugin.print(String string); 
  
  
As with all cordova Plugins the ThermalPrinterPlugin will only be available after deviceReady(), you will then have first to call ThermalPrinterPlugin.init()
before being able to use the ThermalPrinterPlugin.print(String string). Use \n in your print String to add a newline. 

