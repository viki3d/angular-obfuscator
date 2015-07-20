
	var testApp = angular.module("myApp", []);
	testApp.controller("testController", function($scope) {
		$scope.myVar = 1;
		
		$scope.bumpUp = function() {
			$scope.myVar++;
		}
		
		$scope.bumpDown = function() {
			if ($scope.myVar>0) {
				$scope.myVar--;
			}
		}
		
	});
	
