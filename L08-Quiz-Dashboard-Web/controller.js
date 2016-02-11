var app = angular.module("sampleApp", ["firebase"]);

app.controller("QuizCtrl", function($scope, $firebaseArray) {
  var ref = new Firebase("https://fiery-torch-1028.firebaseio.com/submission");


  function authHandler(error, authData) {
    if (error) {
      console.log("Login Failed!", error);
    } else {
      // download the data into a local object
      var query = ref.orderByChild("numCorrect");
      $scope.submissions = $firebaseArray(query);
    }
  }

  ref.authWithPassword({
    email    : 'yusun@csupomona.edu',
    password : '12345'
  }, authHandler);




});
