/**
 * Created by admin on 7/9/18.
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

/**
 * Created by admin on 6/3/18.
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */
mainApp.controller('testController', function($scope, $http) {
    $scope.message = "This page will be reverse";
    $scope.name = "";
    $scope.reverseme = function reverseme() {
        var rv = $scope.name;
        console.log(rv)
        $http({
            method : "GET",
            url : "http://localhost:8080/numbers"
        }).then(function mySuccess(response) {
            var test = response.data;
            console.log(test);
            $scope.succ = test;
        }, function myError(response) {
            var test = response.data;
            console.log(test);
            $scope.err = response.response;
        });
    }
});

// var mainApp = angular.module('mainApp', ['ng-fusioncharts']);
// angular.module('mainApp').controller('operatorController', ['$rootScope', '$scope', function ($scope, $http) {

mainApp.controller('operatorController', function($scope, $http) {
    $scope.message = "This page will be reverse";
    $scope.name = "";

    $scope.operator = function operator() {

        var rv = $scope.name;
        console.log(rv)
        $http({
            method : "GET",
            url : "http://localhost:8080/numbers"
        }).then(function mySuccess(response) {
            var test = response.data;
            console.log(test);

            $scope.myDataSource = {
                chart: {
                    caption: "CALLS ACCORDING TO TELCOS",
                    // subcaption: "Last Year",
                    startingangle: "120",
                    showlabels: "0",
                    showlegend: "1",
                    enablemultislicing: "0",
                    slicingdistance: "15",
                    showpercentvalues: "1",
                    showpercentintooltip: "0",
                    plottooltext: "Network : $label Total calls : $datavalue",
                    theme: "fint"
                },
                data: [
                    {
                        label: "YU",
                        value: "1250400"
                    },
                    {
                        label: "AIRTEL",
                        value: "1463300"
                    },
                    {
                        label: "SAFARICOM",
                        value: "1050700"
                    }
                ]
            }

            Array.prototype.sum = function (prop) {
                var total = 0
                for ( var i = 0, _len = this.length; i < _len; i++ ) {
                    total += this[i][prop]
                }
                return total
            }


            $scope.succ = test;
        }, function myError(response) {
            var test = response.data;
            console.log(test);
            $scope.err = response.response;
        });
    }
});

