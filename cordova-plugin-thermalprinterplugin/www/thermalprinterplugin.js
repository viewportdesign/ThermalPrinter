// JavaScript Document
// Empty constructor
function ThermalPrinterPlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
ThermalPrinterPlugin.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'ThermalPrinterPlugin', 'show', [options]);
}

// Installation constructor that binds ToastyPlugin to window
ThermalPrinterPlugin.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.thermalprinterplugin = new ThermalPrinterPlugin();
  return window.plugins.thermalprinterplugin;
};
cordova.addConstructor(ThermalPrinterPlugin.install);