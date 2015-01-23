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


angular.module("myApp", [])
  .controller("myAppController", AppController)

initData = ($scope) =>
  $scope.data = {}
  $scope.data.speed_Kmh = 0
  $scope.data.gear = 0
  $scope.data.engineRPM = 0