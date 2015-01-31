AppController = ($scope, $http, $interval) ->

  $scope.connected = false

  initData($scope)

#  schedulers
  checkInterval = null
  infoInterval = null

  $scope.startConnection = ->
    request = $http.get '/start'
    request.then () =>
      $scope.connecting = true

      checkInterval = $interval(CheckConnectivity, 2000);

  CheckConnectivity = () =>
    request = $http.get '/checkConnection'
    request.then (result) =>
      $scope.connected = result.data
      if ($scope.connected)
        $scope.connecting = false
        $interval.cancel checkInterval

        infoInterval = $interval(getACData, 1000);
    request.error () =>
      $scope.connectionError = true
      $interval.cancel checkInterval


  getACData = () =>
    request = $http.get '/data'
    request.then (result) =>
      $scope.data = result.data
    request.error () =>
      $scope.connectionError = true
      $interval.cancel infoInterval

LapFormatter = ->
  (lapMs) ->
    #"00:00.000"
    milliseconds = lapMs % 1000;
    if milliseconds < 10
      milliseconds = "00#{milliseconds}"
    else if milliseconds < 100
      milliseconds = "0#{milliseconds}"

    seconds = Math.floor((lapMs / 1000) % 60);
    if seconds < 10
      seconds = "0#{seconds}"
    minutes = Math.floor((lapMs / (60 * 1000)) % 60);
    if minutes < 10
      minutes = "0#{minutes}"

    minutes + ":" + seconds + "." + milliseconds;


app = angular.module("myApp", [])
app.controller "myAppController", AppController
app.filter "lapFormatter", LapFormatter

initData = ($scope) =>
  $scope.data = {}
  $scope.data.speed_Kmh = 0
  $scope.data.gear = 0
  $scope.data.engineRPM = 0
  $scope.data.lastLap = 0
  $scope.data.bestLap = 0
  $scope.data.lapCount = 0
  $scope.data.position = 0