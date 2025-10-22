var exec = require('cordova/exec');

var PLUGIN_NAME = "NTESQuickPassModule";

function isFunction(obj) {
  return !!(obj && obj.constructor && obj.call && obj.apply);
};

var YidunQuickPass = function(options, cb) {
  if (!options) {
    if (isFunction(onCallback)) {
      onCallback(new Error ('businessId is required'))
    } else {
      throw new Error('businessId is required')
    }
    return
  }

  options.loginType = options.loginType || 'quickPass';
  options.timeout = options.timeout || 30000;
  options.businessId = options.businessId;

  exec(cb, cb, PLUGIN_NAME, "init", [
    options.businessId,
    options.timeout,
    options.loginType
  ]);

};

YidunQuickPass.prototype.setUiConfig = function(config, cb) {
    Cordova.exec(cb, cb, PLUGIN_NAME, "setUiConfig",[config]);
};
  
YidunQuickPass.prototype.preFetchNumber = function(cb) {
    exec(cb, cb, PLUGIN_NAME, "preFetchNumber",[]);
};

YidunQuickPass.prototype.login = function(cb) {
    exec(cb, cb, PLUGIN_NAME, "login",[]);
};

YidunQuickPass.prototype.closeAuthController = function(cb) {
    exec(cb, cb, PLUGIN_NAME, "closeAuthController",[]);
};

YidunQuickPass.prototype.numberVerify = function(number, cb) {
    exec(cb, cb, PLUGIN_NAME, "numberVerify",[number]);
};

YidunQuickPass.shouldQuickLogin = function(cb) {
    exec(cb , cb, PLUGIN_NAME, "shouldQuickLogin",[]);
}

module.exports = YidunQuickPass;

