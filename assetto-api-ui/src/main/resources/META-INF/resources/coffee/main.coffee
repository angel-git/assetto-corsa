AppController = ($scope, $http, $interval) ->

  $scope.connected = false
  checkInterval = null

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
        #TODO start showing info
    request.error () =>
      $scope.connectionError = true
      $interval.cancel checkInterval



angular.module("myApp", [])
  .controller("myAppController", AppController)

